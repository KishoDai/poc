package org.poc.neo4jpoc

import org.neo4j.driver.v1.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

@Component
class PocEtlService {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PocEtlService.class)

    @Value('${neo4j_url}')
    private String neo4jUrl
    @Value('${neo4j_username}')
    private String neo4jUsername
    @Value('${neo4j_password}')
    private String neo4jPassword

    @Value('${thread_count}')
    private int threadCount
    @Value('${batch_size}')
    private int batchSize

    @Value('${count_every_thread}')
    private int countEveryThread

    private Driver driver

    void start() {
        long start = System.currentTimeMillis()
        createVertex()
//        createEdge()
        LOG.info('total used time->{}ms', (System.currentTimeMillis() - start))
    }

    private void createVertex() {
        long start0 = System.currentTimeMillis()
        ExecutorService es = Executors.newFixedThreadPool(threadCount)
        List<Future> futures = []
        threadCount.times {
            threadNum ->
                futures.add(es.submit({
                    Session session = driver.session()
                    Transaction tx = session.beginTransaction()
                    int start = threadNum * countEveryThread + 1
                    int end = start + countEveryThread
                    long startTime = System.currentTimeMillis()
                    try {
                        while (start <= end) {
                            tx.run('create (p:Person) set p.id=$id', [id: start])
                            if (start % batchSize == 0) {
                                tx.success()
                                tx.close()
                                LOG.info('vertex:start->{},count->{},used time->{}ms', start, batchSize, (System.currentTimeMillis() - startTime))
                                tx = session.beginTransaction()
                                startTime = System.currentTimeMillis()
                            }
                            start++
                        }
                    } finally {
                        session.close()
                    }
                }))
        }
        futures.each { it.get() }
        LOG.info('vertex: count->{},total used time->{}ms', threadCount * countEveryThread, (System.currentTimeMillis() - start0))
    }

    private void createEdge() {
        long start0 = System.currentTimeMillis()
        ExecutorService es = Executors.newFixedThreadPool(threadCount)
        List<Future> futures = []
        threadCount.times {
            threadNum ->
                futures.add(es.submit({
                    Session session = driver.session()
                    Transaction tx = session.beginTransaction()
                    int start = threadNum * countEveryThread + 1
                    int end = start + countEveryThread
                    long startTime = System.currentTimeMillis()
                    try {
                        while (start <= end) {
                            tx.run('match (p1:Person{id:$id1})-[:HasFriend]->(p2:Person{id:$id2})', [id1: start, id2: start])
                            if (start % batchSize == 0) {
                                tx.success()
                                tx.close()
                                LOG.info('edge:start->{},count->{},used time->{}ms', start, batchSize, (System.currentTimeMillis() - startTime))
                                tx = session.beginTransaction()
                                startTime = System.currentTimeMillis()
                            }
                            start++
                        }
                    } finally {
                        session.close()
                    }
                }))
        }
        futures.each { it.get() }
        LOG.info('edge: count->{},total used time->{}ms', threadCount * countEveryThread, (System.currentTimeMillis() - start0))
    }

    @PostConstruct
    private void afterPropertiesSet() {
        driver = GraphDatabase.driver(neo4jUrl, AuthTokens.basic(neo4jUsername, neo4jPassword))
    }

    static void main(args){
        println('hi')
    }

}
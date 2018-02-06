package org.poc.orientdbpoc3

import com.orientechnologies.orient.core.config.OGlobalConfiguration
import com.orientechnologies.orient.core.db.ODatabasePool
import com.orientechnologies.orient.core.db.ODatabaseSession
import com.orientechnologies.orient.core.db.OrientDB
import com.orientechnologies.orient.core.db.OrientDBConfig
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by kisho on 2018/1/25.
 */
Logger LOG = LoggerFactory.getLogger(this.getClass())
OrientDBConfig config = new OrientDBConfig()
//config.getConfigurations().setValue(OGlobalConfiguration.CLIENT_CHANNEL_MIN_POOL)
ODatabasePool pool = new ODatabasePool(new OrientDB('remote:99.48.88.106', config),
        'test',
        'root',
        'root')

String templateSql = 'INSERT INTO Friend CLUSTER %s SET name = %s;'
List<String> clusters = ['friend', 'friend_1', 'friend_2', 'friend_3', 'friend_4', 'friend_5', 'friend_6', 'friend_7', 'friend_8', 'friend_9', 'friend_10', 'friend_11']

ExecutorService es = Executors.newCachedThreadPool()

int count4Thread = 100000000
int batchSize = 10000
for (int index = 0; index < clusters.size(); index++) {
    int threadIndex = index
//    es.submit({
    ODatabaseSession session = pool.acquire()
    long startTime0 = System.currentTimeMillis()
    int start = threadIndex * count4Thread
    int end = (threadIndex + 1) * count4Thread
    LOG.info('start->{},end->{}', start, end)
    StringBuilder builder = new StringBuilder()
    while (++start <= end) {
        builder.append(String.format(templateSql, clusters.get(threadIndex), start))
        if (start % batchSize == 0) {
            String script = builder.toString()
            long startTime = System.currentTimeMillis()
            session.execute('sql', script)
            LOG.info('{}/{}ms,{}/{}ms', batchSize, (System.currentTimeMillis() - startTime), start, (System.currentTimeMillis() - startTime0))
            builder.delete(0, script.length())
        }
    }
    session.close()
//    })
}


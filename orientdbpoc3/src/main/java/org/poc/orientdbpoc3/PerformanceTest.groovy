package org.poc.orientdbpoc3

import com.orientechnologies.orient.core.db.ODatabasePool
import com.orientechnologies.orient.core.db.ODatabaseSession
import com.orientechnologies.orient.core.db.OrientDB
import com.orientechnologies.orient.core.db.OrientDBConfig
import com.orientechnologies.orient.core.record.OElement
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

/**
 * Created by kisho on 2018/1/25.
 */
Logger LOG = LoggerFactory.getLogger(this.getClass())
OrientDBConfig config = new OrientDBConfig()
ODatabasePool pool = new ODatabasePool(new OrientDB('remote:localhost', config),
        'demodb',
        'root',
        'root')
ExecutorService es = Executors.newCachedThreadPool()
List<Future> futures = []

long start0 = System.currentTimeMillis()
10.times {
    futures.add(es.submit({
        ODatabaseSession session = pool.acquire()
        100.times {
            long start = System.currentTimeMillis()
            session.begin()
            10000.times {
                OElement element = session.newElement('Phone')
                element.setProperty('phone', it)
                element.save()
            }
            session.commit()
            LOG.info('count->{},used time->{}ms', 10000, (System.currentTimeMillis() - start))
        }
        session.close()
    }))
}
futures.each { it.get() }

LOG.info('total->{},used time->{}ms', 1000 * 10000, (System.currentTimeMillis() - start0))

LOG.info('finish!')


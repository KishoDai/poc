package org.poc.orientdbpoc

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx
import com.orientechnologies.orient.core.sql.OCommandSQL
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

/**
 * Created by Administrator on 2017/7/27 0027.
 */
OrientGraphFactory factory = new OrientGraphFactory('remote:localhost/sns', 'root', 'admin')
factory.setupPool(1, 10)

long start = System.currentTimeMillis()
ExecutorService es = Executors.newFixedThreadPool(10)
List<Future> futures = []
10.times {
    index1 ->
        futures.add(es.submit({
            ODatabaseDocumentTx tx = factory.getDatabase()
            tx.begin()
            1000.times {
                index2 ->
                    tx.command(new OCommandSQL('create vertex Phone set phone = ?')).execute(index1 * 1000 + index2 + 1)
            }
            println("Insert  1000 nodes used time :${System.currentTimeMillis() - start}ms")
            tx.commit()
        }))
}
futures.each {
    it.get()
}
es.shutdown()
println("Insert 10000 nodes used time :${System.currentTimeMillis() - start}ms")
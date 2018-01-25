package org.poc.orientdbpoc

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx
import com.orientechnologies.orient.core.intent.OIntentMassiveInsert
import com.orientechnologies.orient.core.record.impl.ODocument
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by kisho on 2018/1/25.
 */
class GenerateData {

    static void main(args) {
        List<String> clusters = ['friend', 'friend_1', 'friend_2', 'friend_3', 'friend_4', 'friend_5', 'friend_6', 'friend_7', 'friend_8', 'friend_9', 'friend_10', 'friend_11']
        ExecutorService es = Executors.newCachedThreadPool()

        OrientGraphFactory factory = new OrientGraphFactory('remote:99.48.88.106:2425/test2', 'root', 'root')
        factory.setupPool(1, clusters.size())


        int total = 1000000000
        clusters.size().times {
            clusterIndex ->
                es.submit({
                    int i = 0
                    ODatabaseDocumentTx database = factory.getDatabase()
//                    long startTime = System.currentTimeMillis()
                    while (++i <= total && (i % clusters.size()) == clusterIndex) {
                        database.declareIntent(new OIntentMassiveInsert())
                        ODocument doc = database.newInstance('Friend')
                        doc.field('name', i)
//                        if (i % 1000 == 0) {
//                            println('insert ' + i + ' used time:' + (System.currentTimeMillis() - startTime) + 'ms')
//                        }
                        doc.save()
                    }
                    database.declareIntent(null)
                })
        }

    }

}

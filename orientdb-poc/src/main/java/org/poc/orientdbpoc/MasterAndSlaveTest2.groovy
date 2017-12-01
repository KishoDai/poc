package org.poc.orientdbpoc

import com.orientechnologies.orient.core.sql.OCommandSQL
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

OrientGraphFactory factory = new OrientGraphFactory('remote:localhost:2424/bill99;remote:localhost:2425/bill99', 'root', 'admin')
factory.setupPool(1, 9)


int count = 1000000

List<String> sqls = ['update Address set name = ? upsert where name = ?',
                     'update Apply set applyNo = ? upsert where applyNo = ?',
                     'update BankCard set cardNo = ? upsert where cardNo = ?',
                     'update Company set name = ? upsert where name = ?',
                     'update Device set deviceId = ? upsert where deviceId = ?',
                     'update Ip set ip = ? upsert where ip = ?',
                     'update Member set memberId = ? upsert where memberId = ?',
                     'update Phone set phone = ? upsert where phone = ?',
                     'update PhoneMark set name = ? upsert where name = ?']

ExecutorService es = Executors.newFixedThreadPool(sqls.size())
List<Future> futures = new ArrayList<>(sqls.size())
sqls.each {
    sql ->
        futures.add(es.submit({
            long start = System.currentTimeMillis()
            count.times {
                i ->
                    factory.getDatabase().command(new OCommandSQL(sql)).execute(i, i)
                    if (i % 1000 == 0) {
                        println("${sql}->used time:${System.currentTimeMillis() - start}ms on ${i}")
                    }
            }
        }))
}
try {
    futures.each {
        it.get()
    }
} catch (Exception e) {
    e.printStackTrace()
    es.shutdownNow()
}

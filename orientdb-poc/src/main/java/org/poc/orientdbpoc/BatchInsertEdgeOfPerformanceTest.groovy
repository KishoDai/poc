package org.poc.orientdbpoc

import com.orientechnologies.orient.core.command.script.OCommandScript
import com.orientechnologies.orient.core.record.impl.ODocument
import com.orientechnologies.orient.core.sql.OCommandSQL
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory

OrientGraphFactory factory = new OrientGraphFactory('remote:99.48.88.106/bill99-antifraud', 'admin', 'admin')
factory.setupPool(1, 1)

long s0 = System.currentTimeMillis()
Map<String, String> phoneRidMap = new HashMap<>(20001)
List<ODocument> phoneDocs = factory.getDatabase().command(new OCommandSQL('select from Phone')).execute()
println("query use time ${System.currentTimeMillis() - s0}ms")
phoneDocs.each {
    phoneRidMap.put(it.field('phone').toString(), it.getIdentity().toString())
}
println("phone size:${phoneDocs.size()}")

long start = System.currentTimeMillis()
int count = 20000

StringBuilder builder = new StringBuilder()
builder.append('begin\n')
count.times {
    //1. use time 15429ms
//    builder.append("create edge CallTo from (select from Phone where phone = '${it}') to (select from Phone where phone= '${it}')\n")

    //2. use time 6221ms
    builder.append("create edge CallTo from ${phoneRidMap[it + '']} to ${phoneRidMap[it + '']}\n")
}
builder.append('commit retry 100\n')
builder.append('return 1')

factory.getDatabase().command(new OCommandScript("sql", builder.toString())).execute()
println("batch insert edges use time ${System.currentTimeMillis() - start}ms")
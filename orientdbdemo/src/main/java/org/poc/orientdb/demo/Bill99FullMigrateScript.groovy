package org.poc.orientdb.demo

import com.orientechnologies.orient.core.command.script.OCommandScript
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx
import com.orientechnologies.orient.core.record.impl.ODocument

/**
 * Created by kisho on 2017/9/1.
 */

long start = System.currentTimeMillis()
int i = 20000
ODatabaseDocumentTx tx = OrientSqlUtil.getDatabase()
List<String> phones = new ArrayList<>(i)

StringBuilder builder = new StringBuilder()
builder.append("begin\n")
i.times {
    phones.add(it)
    builder.append('update Phone set phone=')
            .append('\'')
            .append(it)
            .append('\'')
            .append('upsert return after where phone=')
            .append('\'')
            .append(it)
            .append('\'\n')
}
builder.append('commit retry 100\n')
        .append('return (select from Phone where phone in')
        .append(phones.toString())
        .append(')')
println(builder.toString())
List<ODocument> docs = tx.command(new OCommandScript("sql", builder.toString())).execute()
println(docs.size())
//docs.each {
//    println(it.getIdentity().toString())
//}

//106/10000/单个/35133ms
//106/10000/batch/
println("use time ${System.currentTimeMillis() - start}ms")

println('创建成功!')
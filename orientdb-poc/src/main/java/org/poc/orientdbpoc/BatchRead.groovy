package org.poc.orientdbpoc

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx
import com.orientechnologies.orient.core.record.impl.ODocument
import com.orientechnologies.orient.core.sql.OCommandSQL
import com.tinkerpop.blueprints.impls.orient.OrientGraph
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx
import com.tinkerpop.blueprints.impls.orient.OrientVertex

/**
 * Created by kisho on 2018/2/8.
 */
OrientGraphFactory factory = new OrientGraphFactory('remote:99.48.88.106/hw-demo', 'root', 'root')
factory.setupPool(1, 2)
OrientGraphNoTx graphNoTx = factory.getNoTx()
ODatabaseDocumentTx tx = factory.getDatabase()
OrientGraph graphTx = factory.getTx()
graphTx.setAutoStartTx(false)
int vertexCount = 10000
List<String> phones = new ArrayList<>(vertexCount)
vertexCount.times { phones.add(it + 500000 + '') }
long start = System.currentTimeMillis()
long start1 = System.currentTimeMillis()
Iterator<OrientVertex> iterator = graphTx.command(new OCommandSQL('select from Phone where phone in ' + phones)).execute().iterator()
while(iterator.hasNext()){
    println(iterator.next().getProperty('phone'))
}
println('get by index used time->' + (System.currentTimeMillis() - start1) + 'ms')
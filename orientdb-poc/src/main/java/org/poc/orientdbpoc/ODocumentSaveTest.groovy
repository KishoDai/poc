package org.poc.orientdbpoc

import com.orientechnologies.orient.core.record.impl.ODocument
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory

OrientGraphFactory factory = new OrientGraphFactory('remote:99.48.88.106/bill99-antifraud', 'admin', 'admin')
factory.setupPool(1, 1)

long s0 = System.currentTimeMillis()
Map<String, String> phoneRidMap = new HashMap<>(20001)
List<ODocument> phoneDocs = factory.getDatabase().query(new OSQLSynchQuery<Object>('select from Phone where phone=?'),
        '0')

phoneDocs.get(0).field('province', '上海')
phoneDocs.get(0).save()
println("use time ${System.currentTimeMillis() - s0}ms")

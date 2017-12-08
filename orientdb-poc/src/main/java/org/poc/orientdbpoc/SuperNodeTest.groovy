package org.poc.orientdbpoc

import com.orientechnologies.orient.core.command.OCommandRequest
import com.orientechnologies.orient.core.record.impl.ODocument
import com.orientechnologies.orient.core.sql.OCommandSQL
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory

OrientGraphFactory factory = new OrientGraphFactory('remote:10.80.178.76/sns', 'kisho', 'kisho123')
//factory.setupPool(1, 1)
OCommandRequest request = factory.getDatabase().command(new OCommandSQL('select out("HasPhoneMark").mark as mark from #87:73158'))
//request.setFetchPlan("in_*:1 out_*:1")
Iterable iterable = request.execute()
Iterator<ODocument> it = iterable.iterator()

ODocument doc = it.next()
println(doc.getIdentity().toString())
String mark = doc.field('mark')
println(mark)
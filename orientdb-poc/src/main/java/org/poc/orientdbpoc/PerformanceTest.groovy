package org.poc.orientdbpoc

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx
import com.orientechnologies.orient.core.record.impl.ODocument
import com.orientechnologies.orient.core.sql.OCommandSQL
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory

import java.util.concurrent.Future

/**
 * Created by Administrator on 2017/7/27 0027.
 */
OrientGraphFactory factory = new OrientGraphFactory('remote:10.252.221.8/sns', 'guest', 'guest')
factory.setupPool(1, 1)
println(factory.getDatabase().command(new OCommandSQL("select from V limit 1")).execute())
package org.poc.orientdbpoc

import com.orientechnologies.orient.core.metadata.schema.OSchema
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory

/**
 * Created by Administrator on 2017/7/27 0027.
 */
OrientGraphFactory factory = new OrientGraphFactory('remote:localhost/test', 'admin', 'admin')
factory.setupPool(1, 1)
OSchema oSchema = factory.getDatabase().getMetadata().getSchema()
println(oSchema.getClass('Phone'))
//factory.getDatabase().command(new OCommandSQL('insert into Person set name = ?, data = ?'))
//        .execute('kisho2', '中华人民共和国'.getBytes())
//println('successfully!')
//def list = factory.getDatabase().command(new OCommandSQL('select from Phone')).execute()
//factory.getTx().commit()
//list.each {
//    println(it.field('data'))
//}
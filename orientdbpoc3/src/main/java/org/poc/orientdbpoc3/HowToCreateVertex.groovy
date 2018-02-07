package org.poc.orientdbpoc3

import com.orientechnologies.orient.core.db.ODatabasePool
import com.orientechnologies.orient.core.db.ODatabaseSession
import com.orientechnologies.orient.core.db.OrientDB
import com.orientechnologies.orient.core.db.OrientDBConfig
import com.orientechnologies.orient.core.record.OElement
import com.orientechnologies.orient.core.record.OVertex

/**
 * Created by kisho on 2018/2/7.
 */
ODatabasePool pool = new ODatabasePool(new OrientDB('remote:localhost', OrientDBConfig.defaultConfig()),
        'demodb',
        'root',
        'root')

ODatabaseSession session = pool.acquire()
println('session->' + session)
session.begin()
OVertex vertex = session.newVertex('Phone')
vertex.setProperty('phone', 2)
vertex.save()
OElement element = session.newElement('Phone')
element.setProperty('phone', 3)
element.save()

session.commit()
session.close()
println('session close!')
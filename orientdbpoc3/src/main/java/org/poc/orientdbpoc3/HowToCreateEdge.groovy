package org.poc.orientdbpoc3

import com.orientechnologies.orient.core.db.ODatabasePool
import com.orientechnologies.orient.core.db.ODatabaseSession
import com.orientechnologies.orient.core.db.OrientDB
import com.orientechnologies.orient.core.db.OrientDBConfig
import com.orientechnologies.orient.core.record.OEdge
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
OVertex fromVertex = session.newVertex('Phone')
fromVertex.setProperty('phone', 1)
OVertex toVertex = session.newVertex('Phone')
toVertex.setProperty('phone', 2)
OEdge edge = session.newEdge(fromVertex, toVertex, 'CallTo')
edge.setProperty('callInCnt', 1)
edge.save()
session.commit()
session.close()
println('session close!')
package org.poc.orientdbpoc3

import com.orientechnologies.orient.core.db.ODatabasePool
import com.orientechnologies.orient.core.db.ODatabaseSession
import com.orientechnologies.orient.core.db.OrientDB
import com.orientechnologies.orient.core.db.OrientDBConfig

/**
 * Created by kisho on 2018/2/7.
 */
OrientDB orientDB = new OrientDB('remote:localhost', OrientDBConfig.defaultConfig())
ODatabasePool pool = new ODatabasePool(orientDB,
        'demodb',
        'root',
        'root')

ODatabaseSession session = pool.acquire()
println('session->' + session)
session.begin()
session.commit()
session.close()
println('session close!')
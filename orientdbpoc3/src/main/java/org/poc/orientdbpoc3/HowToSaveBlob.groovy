package org.poc.orientdbpoc3

import com.orientechnologies.orient.core.db.ODatabasePool
import com.orientechnologies.orient.core.db.ODatabaseSession
import com.orientechnologies.orient.core.db.OrientDB
import com.orientechnologies.orient.core.db.OrientDBConfig

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
session.newBlob('中华人民共和国'.getBytes()).save('v')

session.commit()
session.close()
println('session close!')
package org.poc.orientdb.demo

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx
import com.orientechnologies.orient.core.sql.OCommandSQL
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory

/**
 * Created by kisho on 2017/9/1.
 */
final class OrientSqlUtil {

    private static final OrientGraphFactory FACTORY
    static {
        FACTORY = new OrientGraphFactory('remote:localhost/test', 'admin', 'admin')
        FACTORY.setupPool(1, 1) //连接池的最小及最大连接数据
    }

    private OrientSqlUtil() {
        throw new RuntimeException()
    }

    static OrientGraphFactory getFactory() {
        FACTORY
    }

    static ODatabaseDocumentTx getDatabase() {
        getFactory().getDatabase()
    }

    static Object execute(String sql, Object... args) {
        getDatabase().command(new OCommandSQL(sql)).execute(args)
    }

}

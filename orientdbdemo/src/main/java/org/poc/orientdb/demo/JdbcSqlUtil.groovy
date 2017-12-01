package org.poc.orientdb.demo

import com.orientechnologies.orient.jdbc.OrientJdbcConnection

import java.sql.Connection
import java.sql.DriverManager

/**
 * Created by kisho on 2017/9/1.
 */
final class JdbcSqlUtil {

    static void main(args) {
        Properties info = new Properties();
        info.put("user", "admin");
        info.put("password", "admin");
        Connection conn = (OrientJdbcConnection) DriverManager.getConnection("jdbc:orient:remote:99.48.88.106/bdp-metadata", info);
        println(conn.createStatement().execute("select from Schema limit 1"))
    }
}

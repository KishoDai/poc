import groovy.sql.Sql
import org.apache.commons.dbcp2.BasicDataSource

import javax.sql.DataSource

/**
 * Created by kisho on 2017/12/11.
 */
DataSource dataSource = new BasicDataSource()
dataSource.setDriver(Class.forName('com.mysql.jdbc.Driver').newInstance())
dataSource.setUrl('jdbc:mysql://192.168.10.2:3306/alfred?useUnicode=true&amp;characterEncoding=utf-8')
dataSource.setUsername('application_aliuat')
dataSource.setPassword('5tgb^YHN')
dataSource.setInitialSize(1)
dataSource.setMaxTotal(5)

Sql sql = new groovy.sql.Sql(dataSource)
Map<String, String> ssoAppSysUserMap = [
//        'collection-management'        : '14,16,17,33',
//        'coupon-management-web'        : '14,16,18,17,15,12,2,28,27,1,31,32,33,35,13,36,34,46,4,30,59,51,49,55,47,67,62,61,76',
//        'activityManagement'           : '1,2,4,12,14,15,16,17,18,27,28,31,32,33,34,36,38,46,47,49,51,54,61,67',
//        'merchandise-center-management': '12,16,33'
//        'cta':'1,4,5,6,30,32,38,40,46,47,48,54,57,58,60,61,62,63,65,73,75,76,77',
        'anti-fraud-platform-web':'1,9,40,49,50,51,53,54,55,56,58,59,60,61,62,63,64,65,66,68,81'
]
ssoAppSysUserMap.each {
    code, userIdStrs ->
        sql.eachRow('select * from sys_user where id in(' + userIdStrs + ')') {
            row ->
                sql.query('select * from sso_app a inner join sysuser_ssoapp b on a.id = b.ssoapp_id where a.code = \'' + code + '\' and b.sysuser_id =' + row.id) {
                    result ->
                        if (!result.next()) {
                            sql.eachRow('select * from sso_app where code =\'' + code + '\'') {
                                ssoApp ->
                                    println('insert into sysuser_ssoapp set sysuser_id=' + row.id + ',ssoapp_id=' + ssoApp.id + ';')
                            }
                        }
                }
        }

}

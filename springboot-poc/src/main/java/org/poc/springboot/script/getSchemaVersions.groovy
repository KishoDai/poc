package org.poc.springboot.script

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx
import com.orientechnologies.orient.core.sql.OCommandSQL

def executeSql = {
    sql, args ->
        def tx = new ODatabaseDocumentTx('remote:localhost/bdp-metadata')
        tx.open('admin', 'admin')
        tx.command(new OCommandSQL(sql)).execute(args)
}
def parameterStr = '[{"namespace":"com.mime.bdp.dts.accounting","entities":["fss_loans"]}]'


def whereSql = ''
if (parameterStr != null && '' != parameterStr.trim()) {
    def whereSqlBuilder = new StringBuilder()
    groovy.json.JsonSlurper jsonSlurper = new groovy.json.JsonSlurper()
    List parameterList = jsonSlurper.parseText(parameterStr)
    parameterList.each {
        nsParameter ->
            whereSqlBuilder.append(' (linkNamespace.name =\'')
                    .append(nsParameter.namespace)
                    .append('\'')
            if (nsParameter.entities != null && nsParameter.entities.size() > 0) {
                whereSqlBuilder.append(' and name in ')
                        .append(new groovy.json.JsonOutput().toJson(nsParameter.entities))
                        .append(') or')
            }
    }
    whereSql += ' where ' + whereSqlBuilder.toString().substring(0, whereSqlBuilder.toString().lastIndexOf('or'))
}

def result = [:]
String startSql = "select linkNamespace.name as namespace,name as entity, version from Entity  ${whereSql} order by namespace,entity, version asc"
def queryResultList = executeSql(startSql, null)
if (queryResultList == null || queryResultList.size() <= 0) {
    return result
}
queryResultList.each {
    String namespace = it.field('namespace')
    String entity = it.field('entity')
    def version = it.field('version')
    if (result.containsKey(namespace)) {
        if (result[namespace].containsKey(entity)) {
            result[namespace][entity].add(version)
        } else {
            result[namespace][entity] = [version]
        }
    } else {
        result[namespace] = ["${entity}": [version]]
    }
}
result

println(result)



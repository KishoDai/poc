import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx
import com.orientechnologies.orient.core.sql.OCommandSQL

def executeSql = {
    sql, args ->
        def tx = new ODatabaseDocumentTx('remote:localhost/bdp-metadata')
        tx.open('admin', 'admin')
        tx.command(new OCommandSQL(sql)).execute(args)
}
def namespace = 'com.mime.bdp.dts.accounting'
def entity = 'fss_loans'
def version = 1


def result = [:]
String startSql = "select description ,struct,createdDatetime,modifiedDatetime from Entity where linkNamespace.name = '${namespace}' and name = '${entity}' and version = ${version}"
def queryResultList = executeSql(startSql, null)
println(queryResultList)
if (queryResultList == null || queryResultList.size() <= 0) {
    return result
}
result['description'] = queryResultList[0].field('description')
result['createdDatetime'] = queryResultList[0].field('createdDatetime')
result['modifiedDatetime'] = queryResultList[0].field('modifiedDatetime')
def treeGridList = []
def id = 1
queryResultList[0].field('struct').field('fields').each {
    it.id = id++
    treeGridList.add(it)
}
result['treeGridList'] = treeGridList
result



println(result)

package org.poc.orientdbpoc

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx
import com.orientechnologies.orient.core.sql.OCommandSQL
import com.tinkerpop.blueprints.Direction
import com.tinkerpop.blueprints.impls.orient.OrientGraph
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx
import com.tinkerpop.blueprints.impls.orient.OrientVertex

/**
 * Created by kisho on 2018/2/8.
 */
OrientGraphFactory factory = new OrientGraphFactory('remote:99.48.88.106/hw-demo', 'root', 'root')
factory.setupPool(1, 2)
OrientGraphNoTx graphNoTx = factory.getNoTx()
ODatabaseDocumentTx tx = factory.getDatabase()
OrientGraph graphTx = factory.getTx()
graphTx.setAutoStartTx(false)
int vertexCount = 100
List<String> phones = new ArrayList<>(vertexCount)
vertexCount.times { phones.add(it + '') }
long start = System.currentTimeMillis()
long start1 = System.currentTimeMillis()
Iterator<OrientVertex> iterator = graphTx.command(new OCommandSQL('select key,rid.asString() as valueRid from index:Phone.phone where key in ' + phones)).execute().iterator()
println('get by index used time->' + (System.currentTimeMillis() - start1) + 'ms')
List<String> valueRids = new ArrayList<>(vertexCount)

while (iterator.hasNext()) {
    valueRids.add(iterator.next().getProperty('valueRid'))
}
List<OrientVertex> vertices = new ArrayList<>(vertexCount)
long start2 = System.currentTimeMillis()
Iterator<OrientVertex> iterator2 = graphTx.command(new OCommandSQL('select from ' + valueRids)).execute().iterator()
println('get by rid used time->' + (System.currentTimeMillis() - start2) + 'ms')
while (iterator2.hasNext()) {
    vertices.add(iterator2.next())
}

//select $total LET $a=(match{rid:#34:9988}-CallTo->{rid:#34:9989} return '34_9988|34_9989' as a),$b=(match{rid:#34:9976}-CallTo->{rid:#34:9977} return '34_9976|34_9977' as b),$c=(match{rid:#34:9964}-CallTo->{rid:#34:9965} return '34:9964|34:9977' as c),$d=(match{rid:#34:9952}-CallTo->{rid:#34:9953} return '34:9952|34:9953' as d),$e=(match{rid:#34:9952}-CallTo->{rid:#34:9951} return '34:9952|34:9951' as e),$total=unionall($a.a,$b.b,$c.c,$d.d,$e.e) unwind $total

long start3 = System.currentTimeMillis()
//String letTemplateSql = '$a%s=(match{rid:%s}.outE(\'%s\'){where:(createdDatetime is null)}.inV(){rid:%s} return \'%s\' as a%s),'
String letTemplateSql = '$a%s=(select \'%s\' as a%s from (select expand(out_%s) from %s) where in = %s and createdDatetime is null),'

StringBuilder builder = new StringBuilder('select $total LET ')
StringBuilder lastBuilder = new StringBuilder('$total=unionall(')
for (int i = 0; i < vertices.size(); i++) {
    if (i < vertices.size() - 1) {
        String fromRid = vertices.get(i).getId()
        String toRid = vertices.get(i + 1).getId()
//        builder.append(String.format(letTemplateSql, i, fromRid, 'CallTo', toRid, fromRid.replace('#', '') + '|' + toRid.replace('#', ''), i))
        String returnResult = fromRid.replace('#', '').replaceAll(':', '_') + '|' + toRid.replace('#', '').replaceAll(':', '_')
        builder.append(String.format(letTemplateSql, i, returnResult, i, 'CallTo', fromRid, toRid))
        lastBuilder.append('$a').append(i).append('.').append('a').append(i).append(',')
        println(vertices.get(i).getEdges(vertices.get(i + 1), Direction.OUT, 'CallTo').toList())
    }
}
builder.append(lastBuilder.replaceAll(',$', '')).append(') unwind $total')
println(builder.toString())
//List<ODocument> edgeDocs = tx.command(new OCommandSQL(builder.toString())).execute()
//edgeDocs.each {
//    println(it.field('$total'))
//}
println('check edge used time->' + (System.currentTimeMillis() - start3) + 'ms')
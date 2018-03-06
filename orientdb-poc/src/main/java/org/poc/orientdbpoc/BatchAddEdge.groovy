package org.poc.orientdbpoc

import com.orientechnologies.orient.core.sql.OCommandSQL
import com.tinkerpop.blueprints.impls.orient.OrientGraph
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx
import com.tinkerpop.blueprints.impls.orient.OrientVertex

/**
 * Created by kisho on 2018/2/8.
 */
OrientGraphFactory factory = new OrientGraphFactory('remote:99.48.88.106/hw-demo', 'root', 'root')
factory.setupPool(1, 1)
OrientGraphNoTx graphNoTx = factory.getNoTx()
OrientGraph graphTx = factory.getTx()
graphTx.setAutoStartTx(false)
int vertexCount = 1000
List<String> phones = new ArrayList<>(vertexCount)
vertexCount.times { phones.add(it + 8000000 + '') }
long start = System.currentTimeMillis()
long start1 = System.currentTimeMillis()
Iterator<OrientVertex> iterator = graphTx.command(new OCommandSQL('select key,rid.asString() as valueRid from index:Phone.phone where key in ' + phones)).execute().iterator()
println('used time->' + (System.currentTimeMillis() - start1) + 'ms')
List<String> valueRids = new ArrayList<>(vertexCount)

while (iterator.hasNext()) {
    valueRids.add(iterator.next().getProperty('valueRid'))
}
List<OrientVertex> vertices = new ArrayList<>(vertexCount)
long start2 = System.currentTimeMillis()
Iterator<OrientVertex> iterator2 = graphTx.command(new OCommandSQL('select from ' + valueRids)).execute().iterator()
println('used time->' + (System.currentTimeMillis() - start2) + 'ms')
while (iterator2.hasNext()) {
    vertices.add(iterator2.next())
}

graphTx.begin()
for (int i = 0; i < vertices.size(); i++) {
    if (i < vertices.size() - 1) {
        graphTx.addEdge(null, vertices.get(i), new OrientVertex(graphTx, vertices.get(i + 1)), 'CallTo').save()
    }
}
graphTx.commit()
println('used time->' + (System.currentTimeMillis() - start) + 'ms')
package org.poc.orientdbpoc

import com.tinkerpop.blueprints.impls.orient.OrientGraph
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory
import com.tinkerpop.blueprints.impls.orient.OrientVertex

/**
 * Created by kisho on 2018/2/8.
 */
OrientGraphFactory factory = new OrientGraphFactory('remote:99.48.88.106/hw-demo', 'root', 'root')
factory.setupPool(1, 1)
OrientGraph graphTx = factory.getTx()
graphTx.setAutoStartTx(false)

long start = System.currentTimeMillis()
graphTx.begin()
OrientVertex vertex = graphTx.addVertex('class:V')
vertex.setProperty('name', 'hi')
vertex.save()
OrientVertex vertex2 = graphTx.addVertex('class:V')
vertex2.setProperty('name', 'hi2')
vertex2.save()
graphTx.commit()
println('rid->' + vertex.getId())
println('rid->' + vertex2.getId())
println('used time->' + (System.currentTimeMillis() - start) + 'ms')
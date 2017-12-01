package org.poc.orientdbpoc

import com.orientechnologies.orient.core.command.script.OCommandScript
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory

OrientGraphFactory factory = new OrientGraphFactory('remote:localhost/bill99', 'root', 'admin')
factory.setupPool(1, 1)


10.times {
    StringBuilder builder = new StringBuilder()
    builder.append('begin\n')
            .append('let $a = (select from (select expand(out_HasApply) from (select from Member where memberId = 1)) where in = (select from Apply where applyNo=1))\n')
            .append('if($a.size()<=0){\n')
            .append('create edge HasApply from (select from Member where memberId = 1) to (select from Apply where applyNo=1)\n')
            .append('}\n')
            .append('commit retry 100\n')
            .append('return 1\n')
    Long start = System.currentTimeMillis()
    println(builder.toString())
    factory.getDatabase().command(new OCommandScript('sql', builder.toString())).execute()
    println("success,used time->${System.currentTimeMillis() - start}ms")
}
package org.poc.orientdbpoc

import com.orientechnologies.orient.core.command.script.OCommandScript
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory

OrientGraphFactory factory = new OrientGraphFactory('remote:localhost/bill99', 'root', 'admin')
factory.setupPool(1, 1)

String name = '喇叭黄\n小姐'
factory.getDatabase().command(new OCommandScript('sql',
        'begin\n update #148:5 set name = ?\n update #148:5 set name = ?\ncommit retry 100 \n return 1'))
        .execute(name, name)
println("over")
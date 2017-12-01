import com.orientechnologies.orient.core.sql.OCommandSQL
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory

/**
 * Created by kisho on 2017/8/3.
 */
OrientGraphFactory factory = new OrientGraphFactory('remote:localhost/test',
        'admin',
        'admin')
factory.setupPool(1, 1)

long start = System.currentTimeMillis()
1000000.times {
    factory.getDatabase().command(new OCommandSQL('insert into Test set id= ?,name=?')).execute(it, '中华人民共和国' + it)
    println('finished ' + it)
}
println("1000000 insert use time : ${System.currentTimeMillis() - start}ms")

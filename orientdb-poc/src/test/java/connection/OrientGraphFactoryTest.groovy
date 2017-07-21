package connection

import com.orientechnologies.orient.core.record.impl.ODocument
import com.orientechnologies.orient.core.sql.OCommandSQL
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory
import org.junit.Test

/**
 * Created by Administrator on 2017/7/16 0016.
 */
class OrientGraphFactoryTest {


    @Test
    void test() {
        OrientGraphFactory factory = new OrientGraphFactory('remote:localhost/test',
                'admin',
                'admin');
        factory.setupPool(1, 10);
        List<ODocument> docs = factory.getDatabase().command(new OCommandSQL('select from V where ID=?')).execute(1);
        System.out.println(docs);
    }
}

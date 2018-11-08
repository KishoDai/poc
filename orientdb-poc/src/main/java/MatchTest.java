import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;

import java.util.Iterator;

public class MatchTest {
    public static void main(String[] args) {
        OrientGraphFactory factory = new OrientGraphFactory("remote:localhost/demodb", "root", "root");
        OrientGraphNoTx graphNoTx = factory.getNoTx();
        Iterator<OrientVertex> iterator = graphNoTx.command(new OCommandSQL("match {as:c,class:Customers,where:(Phone='+1400844724')} return c.Phone,c.OrderedId")).execute();
        while (iterator.hasNext()) {
            System.out.println();
        }
    }
}

import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.tinkerpop.blueprints.Element;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;

import java.util.Iterator;

public class MatchTest {
    public static void main(String[] args) {
        OrientGraphFactory factory = new OrientGraphFactory("remote:localhost/demodb", "root", "root");
        OrientGraphNoTx graphNoTx = factory.getNoTx();
        Iterable<Element> iterable = graphNoTx.command(
                new OCommandSQL(
                        "match {as:c,class:Customers,where:(Phone='+1400844724')} return c.Phone as phone,c.OrderedId as orderedId"
                )).execute();
        Iterator<Element> it = iterable.iterator();
        while (it.hasNext()) {
            Element ele = it.next();
            System.out.println("Phone=>" + ele.getProperty("phone") + ",OrderedId=>" + ele
                    .getProperty("orderedId"));
        }
        graphNoTx.shutdown();
        factory.close();
    }
}

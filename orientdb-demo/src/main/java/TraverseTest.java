import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.tinkerpop.blueprints.Element;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;

import java.util.Iterator;

public class TraverseTest {
    public static void main(String[] args) {
        OrientGraphFactory factory = new OrientGraphFactory("remote:localhost/demodb", "root", "root");
        OrientGraphNoTx graphNoTx = factory.getNoTx();
        Iterable<Element> iterable = graphNoTx.command(
                new OCommandSQL(
                        "TRAVERSE * FROM (SELECT * FROM Profiles where Id = 1) LIMIT 10"
                )).execute();
        Iterator<Element> it = iterable.iterator();
        while (it.hasNext()) {
            Element ele = it.next();
            System.out.println("@class=>" + ele.getProperty("@class") + ",rid=>" + ele.getId());
        }
        graphNoTx.shutdown();
        factory.close();
    }
}

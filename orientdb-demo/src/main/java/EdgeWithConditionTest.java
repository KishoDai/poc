import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.tinkerpop.blueprints.Element;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;

import java.util.Iterator;

public class EdgeWithConditionTest {
    public static void main(String[] args) {
        OrientGraphFactory factory = new OrientGraphFactory("remote:localhost/demodb", "root", "root");
        OrientGraphNoTx graphNoTx = factory.getNoTx();
        Iterable<Element> iterable = graphNoTx.command(
                new OCommandSQL(
                        "match " +
                                "   {as:customer,class:Customers}-HasProfile->{as:profile}.bothE('HasFriend'){where:($currentMatch.From = '2010-01-07')}.bothV(){as:friend}" +
                                "return distinct customer,profile,friend"
                )).execute();
        Iterator<Element> it = iterable.iterator();
        while (it.hasNext()) {
            Element ele = it.next();
            System.out.println("customer=>" + ele.getProperty("customer") + ",profile=>" + ele
                    .getProperty("profile"));
        }
        graphNoTx.shutdown();
        factory.close();
    }
}

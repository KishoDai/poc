package connection

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx
import com.orientechnologies.orient.core.record.impl.ODocument
import com.orientechnologies.orient.core.sql.OCommandSQL
import org.junit.Test

/**
 * Created by Administrator on 2017/7/16 0016.
 */
class ODatabaseDocumentTxTest {

    @Test
    void test1() {
        def tx = new ODatabaseDocumentTx('remote:localhost/test')
        tx.open('root', 'admin')
        List<ODocument> docList = tx.command(new OCommandSQL('select from V')).execute()
        docList.each {
            println(it.field('@rid').getIdentity().toString())
        }
    }

}

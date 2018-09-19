import org.junit.Test;

import java.io.Serializable;

public class ReferenceTypeTest {

    @Test
    public void test() {
        //类类型
        Object obj = new Object();
        //数组类型
        Object[] objArr = new Object[4];
        // 接口类型
        Serializable serializable = new Serializable() {
        };
    }

}

import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;

public class ReferenceNullValueTest {

    @Test
    public void test() {
        //没有指向任何对象
        Object obj = null;

        // null可强制转换成任何类型
        String s = (String) obj;
        Assert.assertNull(s);
        Integer i = (Integer) obj;
        Assert.assertNull(i);

        // 但是null不属于任何类型
        Assert.assertFalse(null instanceof Object);
        Assert.assertFalse(null instanceof String);
        Assert.assertFalse(null instanceof Integer);
    }

}

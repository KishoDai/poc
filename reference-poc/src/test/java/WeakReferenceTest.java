import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class WeakReferenceTest {

    /**
     * -Xms10m -Xmx10m  -XX:+PrintHeapAtGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError
     */
    @Test(expected = OutOfMemoryError.class)
    public void testSoftReferenceWithoutQueue() {
        int size = 300000;
        List<SoftReference<Integer>> list = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            list.add(new SoftReference<Integer>(i));
        }

        int nullObjCount = 0;
        Assert.assertEquals(size, list.size());
        for (int i = 0; i < size; i++) {
            if (list.get(i).get() == null) {
                nullObjCount++;
            }
        }
        System.out.println("nullObjCount = " + nullObjCount);
        Assert.assertTrue(nullObjCount <= size);

        System.gc();

        nullObjCount = 0;
        Assert.assertEquals(size, size);
        for (int i = 0; i < size; i++) {
            if (list.get(i).get() == null) {
                nullObjCount++;
            }
        }
        System.out.println("nullObjCount = " + nullObjCount);
        Assert.assertTrue(nullObjCount < size);
    }

}

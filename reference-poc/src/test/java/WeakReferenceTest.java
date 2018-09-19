import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class WeakReferenceTest {

    private class Student {
        private int id;
        private String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" + "id=" + id + ", name='" + name + '\'' + '}';
        }
    }

    /**
     * JVM参数：
     * -Xms10m -Xmx10m  -XX:+PrintHeapAtGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError
     */
    @Test
    public void testWeakReferenceWithoutQueue() {
        int weakRefSize = 1000;
        List<WeakReference> list = new ArrayList<WeakReference>(weakRefSize);
        for (int i = 0; i < weakRefSize; i++) {
            // 注意此处创建的Student对象除了WeakReference没有其它强引用
            // 若此处Student还没其它GC root对象关联，那么它仍然是强引用对象
            list.add(new WeakReference<Student>(new Student(i, "张三")));
        }

        // 可用内存充足，没有发生GC
        Assert.assertEquals(0, getGcObjectSize(list));

        // 强制执行gc,大部分情况下垃圾回收器都会立即执行，若没有执行，请在gc后sleep一段时间
        System.gc();

        // 不管内存情况是否充足，软引用对象都会被垃圾回收器回收
        Assert.assertEquals(weakRefSize, getGcObjectSize(list));
    }

    // 基于队列的测试同SoftReferenceTest#testPhantomReferenceWithQueue

    /**
     * 获取被垃圾回收器清除的对象数量
     *
     * @param list
     * @return
     */
    private int getGcObjectSize(List<WeakReference> list) {
        int gcStuSize = 0;
        for (WeakReference sr : list) {
            if (sr.get() == null) {
                gcStuSize++;
            }
        }
        return gcStuSize;
    }

}

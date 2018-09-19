import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class SoftReferenceTest {

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
    public void testSoftReferenceWithoutQueue() {
        int softRefSize = 100000;
        List<SoftReference> list = new ArrayList<SoftReference>(softRefSize);
        for (int i = 0; i < softRefSize; i++) {
            // 注意此处创建的Student对象除了SoftReference没有其它强引用
            // 若此处Student还被其它GC root对象关联，那么它仍然是强引用对象
            list.add(new SoftReference<Student>(new Student(i, "张三")));
        }

        // 可用内存充足，没有发生GC
        Assert.assertEquals(0, getGcObjectSize(list));

        // 强制执行gc,大部分情况下垃圾回收器都会立即执行，若没有执行，请在gc后sleep一段时间
        System.gc();

        // 可用内存充足，垃圾回收器不会回收软引用对象
        Assert.assertEquals(0, getGcObjectSize(list));

        // 创建一个大对象，让heap内存紧张，发生full gc，清除软引用
        byte[] bArr = new byte[1024 * 1024];

        System.out.println("getGcObjectSize(list) = " + getGcObjectSize(list));
        //被垃圾回收器回收的软引用对象的数量>0
        Assert.assertTrue(getGcObjectSize(list) > 0);
    }

    /**
     * JVM参数：
     * -Xms10m -Xmx10m  -XX:+PrintHeapAtGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError
     */
    @Test
    public void testSoftReferenceWithQueue() throws InterruptedException {
        // 定义一个软引用的队列
        final ReferenceQueue<Student> softQueue = new ReferenceQueue<Student>();

        class StudentSoftReference extends SoftReference<Student> {
            private int id;

            public StudentSoftReference(Student referent, ReferenceQueue<? super Student> q) {
                super(referent, q);
                id = referent.id;
            }
        }
        // 启动一个线程，实时追踪对象回收的情况
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        StudentSoftReference sr = (StudentSoftReference) softQueue.remove();
                        System.out.println("Student id " + sr.id + " has been deleted by GC!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.setDaemon(true); //设置为守护线程
        t.start();

        int softRefSize = 10;
        List<SoftReference> list = new ArrayList<SoftReference>(softRefSize);
        for (int i = 0; i < softRefSize; i++) {
            // 若垃圾回收器准备回收一个对象时，如果发现它还有弱引用，就会在回收对象后，将这个弱引用加入引用队列,
            // 以通知应用程序的回收情况
            list.add(new StudentSoftReference(new Student(i, "张三"), softQueue));
        }

        // 可用内存充足，没有发生GC
        Assert.assertEquals(0, getGcObjectSize(list));

        // 强制执行gc,大部分情况下垃圾回收器都会立即执行，若没有执行，请在gc后sleep一段时间
        System.gc();

        // 可用内存充足，垃圾回收器不会回收软引用对象
        Assert.assertEquals(0, getGcObjectSize(list));

        // 创建一个大对象，让内存资源紧张，发生full gc，清除软引用
        byte[] bArr = new byte[1024 * 1010 * 6];

        System.out.println("getGcObjectSize(list) = " + getGcObjectSize(list));
        Assert.assertTrue(getGcObjectSize(list) > 0);

        // 避免主线程退出时，守护线程还没结束
        Thread.sleep(2000);
    }

    /**
     * 获取被垃圾回收器清除的对象数量
     *
     * @param list
     * @return
     */
    private int getGcObjectSize(List<SoftReference> list) {
        int gcStuSize = 0;
        for (SoftReference sr : list) {
            if (sr.get() == null) {
                gcStuSize++;
            }
        }
        return gcStuSize;
    }

}

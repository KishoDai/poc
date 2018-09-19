import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceTest {

    private class Student {
        private int id;
        private String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        // 可以解注下这段代码，看下打印日志。下一篇源码剖析解释下这个。
//        @Override
//        protected void finalize() {
//            System.out.println("Student id " + id + " has been finalize()");
//        }

        @Override
        public String toString() {
            return "Student{" + "id=" + id + ", name='" + name + '\'' + '}';
        }
    }

    /**
     * JVM参数：
     * -XX:+PrintHeapAtGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError
     */
    @Test
    public void testPhantomReferenceWithQueue() {
        final ReferenceQueue<Student> phantomQueue = new ReferenceQueue<Student>();

        class StudentPhantomReference extends PhantomReference<Student> {
            private int id;

            public StudentPhantomReference(Student referent, ReferenceQueue<? super Student> q) {
                super(referent, q);
                id = referent.id;
            }
        }

        // 启动一个线程，实时追踪对象回收的情况
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        StudentPhantomReference sr = (StudentPhantomReference) phantomQueue.remove();
                        System.out.println("Student id " + sr.id + " has been deleted");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.setDaemon(true);//设置为守护线程
        t.start();

        StudentPhantomReference phantomReference = new StudentPhantomReference(new Student(1, "张三"), phantomQueue);

        // 通过get()方法获取到的Student对象为null
        Assert.assertNull(phantomReference.get());

        // 强制执行gc,大部分情况下垃圾回收器都会立即执行，若没有执行，请在gc后sleep一段时间
        System.gc();

        //无论内存是否充足，Student对象都被回收，观察日志打印
    }

}

package org.poc.reference;

import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ReferenceTest {
    @Test
    public void testStrongReference() {
        int size = 10000;
        List<String> list = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            list.add(i + "");
        }

        Assert.assertEquals(size, list.size());

        System.gc();

        Assert.assertEquals(size, list.size());
    }

    /**
     * -XX:+PrintGCDetails -Xms20m -Xmx20m
     */
    @Test
    public void testSoftReference() {
        int size = 300000;
        List<SoftReference<String>> list = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            list.add(new SoftReference<String>(i + ""));
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

    /**
     * -XX:+PrintGCDetails -Xms20m -Xmx20m
     */
    @Test
    public void testWeakReference() {
        int size = 300000;
        List<WeakReference<String>> list = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            list.add(new WeakReference<String>(i + ""));
        }

        int nullObjCount = 0;
        Assert.assertEquals(size, list.size());
        for (int i = 0; i < size; i++) {
            if (list.get(i).get() == null) {
                nullObjCount++;
            }
        }
        System.out.println("nullObjCount = " + nullObjCount);
        Assert.assertTrue(nullObjCount < size);

        System.gc();

        nullObjCount = 0;
        Assert.assertEquals(size, size);
        for (int i = 0; i < size; i++) {
            if (list.get(i).get() == null) {
                nullObjCount++;
            }
        }
        System.out.println("nullObjCount = " + nullObjCount);
        Assert.assertTrue(nullObjCount == size);
    }

    /**
     * -XX:+PrintGCDetails -Xms20m -Xmx20m
     * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintReferenceGC
     */
    @Test
    public void testWeakReference2() throws InterruptedException {
        final AtomicInteger ai = new AtomicInteger(0);
        final ReferenceQueue queue = new ReferenceQueue();
        Object obj = new Object(){};
        WeakReference pr = new WeakReference(obj, queue);

        System.out.println("before gc : " + queue.poll());

        obj = null;
        System.gc();
        Thread.sleep(1000);
        System.out.println("after gc : " + queue.poll());
    }

    /**
     * -XX:+PrintGCDetails -Xms20m -Xmx20m
     * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintReferenceGC
     */
    @Test
    public void testPhantomReference() throws InterruptedException {
        final AtomicInteger ai = new AtomicInteger(0);
        final ReferenceQueue queue = new ReferenceQueue();
        Object obj = new Object(){};
        PhantomReference pr = new PhantomReference(obj, queue);

        Assert.assertNull(pr.get());
        System.out.println("before gc : " + queue.poll());

        obj = null;
        System.gc();
        Thread.sleep(1000);
        System.out.println("after gc : " + queue.poll());
    }

    public static void main(String[] args) throws InterruptedException {
        final ReferenceQueue<Object> queue = new ReferenceQueue();
        WeakReference<Object> wr = new WeakReference(new Object(), queue);
        System.out.println("wr = " + wr);
        System.out.println(wr.get());
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Reference r = queue.poll();
                    if (r != null) {
                        System.out.println("r = " + r);
                        System.out.println("xxx=" + r.get());
                        break;
                    }

                }
            }
        });
        t.start();
        System.runFinalization();
        t.join();
        System.out.println(queue.remove(1000));
        System.out.println(wr);
    }
}

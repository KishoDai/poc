package org.poc.memoryerror;

import org.junit.Test;
import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class OutOfMemoryErrorTest {

    /**
     * 没有足够空间分配大对象。
     * <p>
     * JVM参数：
     * -Xms10m -Xmx10m  -XX:+PrintHeapAtGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\git_code\kisho\poc\jvm-poc\src\heap_dump_path
     */
    @Test(expected = OutOfMemoryError.class)
    public void testHeapUseUpWithBigObject() {
        int length = 864000;
        long[] lArr = new long[length];
    }

    /**
     * 创建太多无法回收的对象。
     * <p>
     * JVM参数：
     * -Xms10m -Xmx10m -XX:+PrintGCApplicationStoppedTime -XX:+PrintHeapAtGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\git_code\kisho\poc\jvm-poc\src\heap_dump_path
     */
    @Test(expected = OutOfMemoryError.class)
    public void testHeapUseUpWithManyObject() {
        int size = 1000000;
        List<Object> objList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            objList.add(new Object());
        }
    }

    /**
     * 使用String.intern()创建太多无法回收的对象。
     * 在JDK1.8并不会导致内存溢出，因为1.8把intern（）后的对象存放在heap中，而不是方法区中
     * <p>
     * JVM参数：
     * -Xms10m -Xmx10m  -XX:+PrintHeapAtGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\git_code\kisho\poc\jvm-poc\src\heap_dump_path
     */
    @Test(expected = OutOfMemoryError.class)
    public void testHeapUseUpWithStringIntern() {
        try {
            int size = 10000000;
            for (int i = 0; i < size; i++) {
                ("hello" + i).intern();
            }
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
            throw error;
        }
    }

    /**
     * JVM参数：
     * -Xss5m -Xms10m -Xmx10m  -XX:+PrintHeapAtGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\git_code\kisho\poc\jvm-poc\src\heap_dump_path
     */
    @Test(expected = OutOfMemoryError.class)
    public void testStackUseUp() {
        try {
            testStack(1);
        } catch (StackOverflowError error) {
            error.printStackTrace();
            throw error;
        }
    }

    /**
     * 该案例证明默认情况下，方法中创建的对象还是在堆上分配的。
     * <p>
     * JVM参数：
     * -Xss100m -Xms10m -Xmx10m  -XX:+PrintHeapAtGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\git_code\kisho\poc\jvm-poc\src\heap_dump_path
     */
    @Test(expected = OutOfMemoryError.class)
    public void testStackUseUp2() throws InterruptedException {
        int threadCount = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    testStack2(1);
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
    }

    /**
     * direct memory用完在抛出OutOfMemoryError之前会执行一次GC.
     * 但不会入文件。
     * <p>
     * JVM参数：
     * -XX:MaxDirectMemorySize=10m -XX:+PrintHeapAtGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\git_code\kisho\poc\jvm-poc\src\heap_dump_path
     */
    @Test(expected = OutOfMemoryError.class)
    public void testOomWithDirectMemoryUseUp() {
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(15 * 1024 * 1024);
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
            throw error;
        }
    }

    private long testStack(int i) {
        byte[] bArr = new byte[6 * 1024];
        return testStack(i);
    }


    private long testStack2(int i) {
        byte[] bArr = new byte[2 * 1024];
        return 1;
    }

}

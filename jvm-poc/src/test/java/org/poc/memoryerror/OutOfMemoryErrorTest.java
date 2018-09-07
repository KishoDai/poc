package org.poc.memoryerror;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
     * -Xms10m -Xmx10m  -XX:+PrintHeapAtGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\git_code\kisho\poc\jvm-poc\src\heap_dump_path
     */
    @Test(expected = OutOfMemoryError.class)
    public void testHeapUseUpWithManyObject() {
        int size = 1000000;
        List<Object> objList = new ArrayList<>(size);
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
     * 使用String.intern()创建太多无法回收的对象。
     * 在JDK1.8并不会导致内存溢出，因为1.8把intern（）后的对象存放在heap中，而不是方法区中
     * <p>
     * JVM参数：
     * -Xms10m -Xmx10m  -XX:+PrintHeapAtGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\git_code\kisho\poc\jvm-poc\src\heap_dump_path
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


    private long testStack(int i) {
        int[] iArr = new int[1000000];
        return testStack(i);
    }

}

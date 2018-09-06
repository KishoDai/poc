package org.poc.memoryerror;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryErrorTest {

    /**
     * JVM参数：
     * -Xms10MB -Xmx10MB  -XX:+PrintHeapAtGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\git_code\kisho\poc\jvm-poc\src\heap_dump_path
     */
    @Test(expected = OutOfMemoryError.class)
    public void testHeapUseUpWithBigObject() {
        int length = 864000;
        long[] lArr = new long[length];
    }

    /**
     * JVM参数：
     * -Xms1MB -Xmx1MB  -XX:+PrintHeapAtGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\git_code\kisho\poc\jvm-poc\src\heap_dump_path
     */
    @Test(expected = OutOfMemoryError.class)
    public void testHeapUseUpWithManyObject() {
        int size = 100000;
        List<Object> objList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            objList.add(new Object());
        }
    }

}

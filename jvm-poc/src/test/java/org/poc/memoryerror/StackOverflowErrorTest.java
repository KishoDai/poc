package org.poc.memoryerror;

import org.junit.Test;

public class StackOverflowErrorTest {

    private int countStack = 0;

    /**
     * JVM参数：
     * -Xss10m -XX:+PrintHeapAtGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\git_code\kisho\poc\jvm-poc\src\heap_dump_path
     */
    @Test(expected = StackOverflowError.class)
    public void testStackUseUp() {
        try {
            testStack(1);
        } catch (StackOverflowError error) {
            System.out.println("countStack = " + countStack);
            error.printStackTrace();
            throw error;
        }
    }

    private long testStack(int i) {
        int j = i;
        int m = 0;
        int x = 1;
        countStack++;
        return testStack(i);
    }

}

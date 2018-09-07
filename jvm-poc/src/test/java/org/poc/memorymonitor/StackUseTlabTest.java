package org.poc.memorymonitor;

import org.junit.Test;

public class StackUseTlabTest {

//    -XX:+UseTLAB    开启TLAB
//    -XX:+PrintTLAB  打印TLAB信息
//    -XX:+TLABSize   设置TLAB大小
//    -XX:TLABRefillWasteFraction 设置维护进入TLAB空间单个对象大小，比例值，默认1/64，对象大于该值会去堆创建。
//            -XX:ResizeTLAB  自调整TLABRefillWasteFraction 阀值。

    /**
     * -Xss10m  -XX:+UseTLAB -XX:+PrintTLAB  -XX:+TLABSize=1m  -XX:+PrintHeapAtGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\git_code\kisho\poc\jvm-poc\src\heap_dump_path
     */
    @Test
    public void testTlab() {
        int[] iArr = new int[100];
    }

    public static void main(String[] args) {
        int[] iArr = new int[10];
    }
}

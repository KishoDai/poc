package org.poc;

public class GcTest {

    private static final int _1MB = 1024 * 1024;

    //-verbose:gc -Xmx20M -Xms20M -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
    public static void main(String[] args) {
        byte[] allocation1 = new byte[2 * _1MB];
        byte[] allocation2 = new byte[2 * _1MB];
        byte[] allocation3 = new byte[2 * _1MB];
        byte[] allocation4 = new byte[4 * _1MB];//出现一次Minor GC(Yong GC)
    }
}

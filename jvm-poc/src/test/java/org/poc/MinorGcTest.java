package org.poc;

public class MinorGcTest {

    private static final int _1MB = 1024 * 1024;

    //-verbose:gc -Xmx20M -Xms20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
    //Yong(10M): eden : 8192KB, Survivor: to space(1024), from space(1024)
    public static void main(String[] args) {
        byte[] allocation1 = new byte[2 * _1MB];
        byte[] allocation2 = new byte[2 * _1MB];
        byte[] allocation3 = new byte[2 * _1MB];
        byte[] allocation4 = new byte[4 * _1MB];//出现一次Minor GC(Yong GC)
//        byte[] allocation5 = new byte[4 * _1MB];//出现一次Minor GC(Yong GC)
//        byte[] allocation6 = new byte[4* _1MB];//出现一次Full GC(Yong GC)
//        byte[] allocation7 = new byte[4* _1MB];//出现一次Minor GC(Yong GC)
    }
}

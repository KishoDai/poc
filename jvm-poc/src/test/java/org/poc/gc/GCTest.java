package org.poc.gc;

import java.util.ArrayList;
import java.util.List;

public class GCTest {
    //-verbose:gc -Xmx20M -Xms20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+PrintHeapAtGC -XX:+PrintGCDateStamps -XX:+PrintGCApplicationStoppedTime
    public static void main(String[] args) throws InterruptedException {
        List<Object> list = new ArrayList<>();
        while (true) {
            list.add(new Object());
//            Thread.sleep(20);
            System.gc();
            Thread.sleep(1000);
        }
    }
}

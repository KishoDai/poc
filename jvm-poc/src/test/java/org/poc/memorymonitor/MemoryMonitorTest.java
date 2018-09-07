package org.poc.memorymonitor;

import java.util.ArrayList;
import java.util.List;

public class MemoryMonitorTest {

    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<OOMObject>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }

    }

    // -Xms100m -Xmx100m -XX:+UseSerialGC
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        fillHeap(1000);
        System.gc();
        while (true) {

        }
    }
}

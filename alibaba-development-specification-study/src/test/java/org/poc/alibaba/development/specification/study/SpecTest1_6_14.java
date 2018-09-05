package org.poc.alibaba.development.specification.study;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpecTest1_6_14 {

    @Test
    public void testHashMap() throws InterruptedException {
        final Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        int threadCount = 100;
        final int opCountPerThreadCount = 100000;

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int i1 = i;
            executor.submit(new Runnable() {
                public void run() {
                    for (int j = 0; j < opCountPerThreadCount; j++) {
                        map.put(i1 + opCountPerThreadCount + j, null);
                    }
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();

        System.out.println(map.size());
    }

}


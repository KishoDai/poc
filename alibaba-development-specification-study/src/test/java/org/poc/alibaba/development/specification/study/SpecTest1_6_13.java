package org.poc.alibaba.development.specification.study;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SpecTest1_6_13 {

    private volatile int count = 0;
    private final AtomicInteger ai = new AtomicInteger(0);

    @Test
    public void testIncIsNotAtomic() throws InterruptedException {

        int threadCount = Runtime.getRuntime().availableProcessors();
        final int opCountPerThreadCount = 10000;

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        for (int j = 0; j < threadCount; j++) {
            executor.submit(new Runnable() {
                public void run() {
                    for (int k = 0; k < opCountPerThreadCount; k++) {
                        SpecTest1_6_13.this.count++;
                    }
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        System.out.println("expect count : " + threadCount * opCountPerThreadCount);
        System.out.println("actual count : " + count);
        Assert.assertTrue(count < threadCount * opCountPerThreadCount);
    }

    @Test
    public void testAtomicInteger() throws InterruptedException {

        int threadCount = Runtime.getRuntime().availableProcessors();
        final int opCountPerThreadCount = 10000;

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        for (int j = 0; j < threadCount; j++) {
            executor.submit(new Runnable() {
                public void run() {
                    for (int k = 0; k < opCountPerThreadCount; k++) {
                        ai.getAndIncrement();
                    }
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        System.out.println("expect count : " + threadCount * opCountPerThreadCount);
        System.out.println("actual count : " + ai.get());
        Assert.assertEquals(threadCount * opCountPerThreadCount, ai.get());
    }


}


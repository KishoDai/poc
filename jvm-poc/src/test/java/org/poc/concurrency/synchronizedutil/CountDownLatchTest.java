package org.poc.concurrency.synchronizedutil;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatchTest countDownLatchTest = new CountDownLatchTest();
        countDownLatchTest.timeTasks(3, new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis());
            }
        });
    }

    public void timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                        task.run();
                    } catch (InterruptedException e) {
                    } finally {
                        endGate.countDown();
                    }
                }
            }.start();
        }

        long start = System.currentTimeMillis();
        startGate.countDown();
        endGate.await();
        System.out.println("used time : " + (System.currentTimeMillis() - start) + "ms");
    }

}

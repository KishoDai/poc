package org.poc.alibaba.development.specification.study;

import java.util.concurrent.CountDownLatch;

public class SpecTest1_6_10 {

    public static void main(String[] args) throws InterruptedException {
        timeTasks(3, new Runnable() {
            public void run() {
                System.out.println(System.currentTimeMillis());
            }
        });
    }

    public static void timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        task.run();
                    } finally {
                        endGate.countDown();
                    }
                }
            }.start();
        }

        long start = System.currentTimeMillis();
        endGate.await();
        System.out.println("used time : " + (System.currentTimeMillis() - start) + "ms");
    }

}


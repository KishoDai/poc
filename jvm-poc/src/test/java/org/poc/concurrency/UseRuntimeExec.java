package org.poc.concurrency;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class UseRuntimeExec {
    public static void main(String[] args) throws InterruptedException {
        int threadCount = 1000;
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        do {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Runtime.getRuntime().exec("calc");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                }
            }).start();
        } while (--threadCount >= 0);
        countDownLatch.await();
    }
}

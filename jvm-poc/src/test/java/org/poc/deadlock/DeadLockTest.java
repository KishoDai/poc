package org.poc.deadlock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class DeadLockTest {

    @Test
            //(timeout = 5000L)
    public void testDeadLockOnObject() throws InterruptedException {
        int threadCount = Runtime.getRuntime().availableProcessors();
        int opCountPerThread = 20;
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        final Foo foo = new Foo();
        for (int i = 0; i < threadCount; i++) {
            final int k = i;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < opCountPerThread; j++) {
                        foo.getConnection(k % 2 == 0);
                    }
                    countDownLatch.countDown();
                }
            });
            t.setName("MyThread " + i);
            t.start();
        }
        countDownLatch.await();
    }


    class Foo {
        private Object lockA = new Object();
        private Object lockB = new Object();

        private void getConnection(boolean c) {

            if (c) {
                synchronized (lockA) {
                    synchronized (lockB) {
                        System.out.println("lockA->lockB c = " + c);
                    }
                }
            } else {
                synchronized (lockB) {
                    synchronized (lockA) {
                        System.out.println("lockB->lockA c = " + c);
                    }
                }
            }

        }

    }

}

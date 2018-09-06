package org.poc.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedAndReentrantLockCompareTest {

    public static void main(String[] args) {

        int THREAD_COUNT = 8;
        final int COUNT = 1000000;
        ExecutorService es = Executors.newCachedThreadPool();
        List<Future> futures = new ArrayList<Future>(THREAD_COUNT);

        futures.clear();
        final IncrementModel model = new IncrementModel();
        long current = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++) {
            futures.add(es.submit(new Runnable() {
                public void run() {
                    for (int j = 0; j < COUNT; j++) {
                        synchronized (model) {
                            model.incrementAndAdd();
                        }
                    }
                }
            }));
        }
        for (Future future : futures) {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("value=>" + model.get());
        System.out.println("synchronized used time:" + (System.currentTimeMillis() - current) + "ms");


        futures.clear();
        final IncrementModel model2 = new IncrementModel();
        final ReentrantLock lock = new ReentrantLock(true);
        current = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++) {
            futures.add(es.submit(new Runnable() {
                public void run() {
                    for (int j = 0; j < COUNT; j++) {
                        lock.lock();
                        try {
                            model2.incrementAndAdd();
                        } finally {
                            lock.unlock();
                        }
                    }
                }
            }));
        }
        for (Future future : futures) {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("value=>" + model2.get());
        System.out.println("ReentrantLock used time:" + (System.currentTimeMillis() - current) + "ms");


        futures.clear();
        final IncrementModel model3 = new IncrementModel();
        current = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++) {
            futures.add(es.submit(new Runnable() {
                public void run() {
                    for (int j = 0; j < COUNT; j++) {
                        model3.incrementAndAdd2();
                    }
                }
            }));
        }
        for (Future future : futures) {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("value=>" + model3.get());
        System.out.println("AtomicInteger used time:" + (System.currentTimeMillis() - current) + "ms");


        es.shutdownNow();
    }

}

class IncrementModel {
    private int i;
    private AtomicInteger ai = new AtomicInteger(0);

    public int incrementAndAdd() {
        return ++i;
    }

    public int get() {
        return i;
    }

    public int incrementAndAdd2() {
        return ai.incrementAndGet();
    }

    public int get2() {
        return ai.get();
    }

}

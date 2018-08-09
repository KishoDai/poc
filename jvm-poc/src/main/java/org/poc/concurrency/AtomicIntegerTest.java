package org.poc.concurrency;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    private static AtomicInteger ai = new AtomicInteger(0);

    public static void main(String[] args) {

        ai.getAndIncrement();
        System.out.println(ai.get());

        int THREADS_COUNT = 20;
        List<Future> futures = new ArrayList<Future>(THREADS_COUNT);
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < THREADS_COUNT; i++) {
            futures.add(es.submit(new Runnable() {
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        ai.incrementAndGet();
                    }
                }
            }));
        }
        for (Future f : futures) {
            try {
                f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(ai.get());
        es.shutdownNow();

    }
}

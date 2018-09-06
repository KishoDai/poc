package org.poc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VolatileTest {

    volatile int i = 0;

    private synchronized void add() {
        i++;
    }

    public static void main(String[] args) {
        final VolatileTest volatileTest = new VolatileTest();
        ExecutorService es = Executors.newCachedThreadPool();
        int threadCount = 20;
        List<Future> futures = new ArrayList<Future>(20);
        final int max = 100000;
        for (int i = 0; i < threadCount; i++) {
            futures.add(es.submit(new Runnable() {
                public void run() {
                    for (int i = 0; i < max; i++) {
                        volatileTest.add();
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
        System.out.println("i=" + volatileTest.i);
        es.shutdownNow();
    }

}

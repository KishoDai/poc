package org.poc.alibaba.development.specification.study;

import org.junit.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SpecTest1_5_3 {

    @Test(expected = ConcurrentModificationException.class)
    public void testConcurrentModificationException() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");

        final List<String> subList = list.subList(1, 2);

        final ExecutorService executor = Executors.newCachedThreadPool();
        int threadCount = 50;
        List<Future> futures = new ArrayList<Future>(threadCount);
        for (int i = 0; i < threadCount; i++) {
            futures.add(executor.submit(new Runnable() {
                public void run() {
                    subList.add("4");
                }
            }));
        }
        for (Future future : futures) {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                throw (ConcurrentModificationException) e.getCause();
            }
        }
    }
}

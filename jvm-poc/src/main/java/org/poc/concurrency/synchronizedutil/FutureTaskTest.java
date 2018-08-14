package org.poc.concurrency.synchronizedutil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hi";
            }
        });
        Thread t = new Thread(task);
        t.start();
        task.get();
    }

}

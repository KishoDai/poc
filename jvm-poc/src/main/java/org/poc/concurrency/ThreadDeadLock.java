package org.poc.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadDeadLock {

    private static ExecutorService exec = Executors.newSingleThreadExecutor();

    public static class RenderPageTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            Future<String> header = exec.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "header";
                }
            });

            Future<String> footer = exec.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "footer";
                }
            });
            return header.get() + " = " + footer.get();
        }
    }

    public static void main(String[] args) throws Exception {
        RenderPageTask task = new ThreadDeadLock.RenderPageTask();
        System.out.println(exec.submit(task).get());
        exec.shutdown();
    }

}

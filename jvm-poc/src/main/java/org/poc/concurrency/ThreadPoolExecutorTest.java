package org.poc.concurrency;

import java.util.concurrent.*;

public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
//        int corePoolSize,
//        int maximumPoolSize,
//        long keepAliveTime,
//        TimeUnit unit,
//        BlockingQueue<Runnable> workQueue,
//        ThreadFactory threadFactory,
//        RejectedExecutionHandler handler
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(1,
                1,
                100000L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("reject r=>" + r);
                    }
                });

        for (int i = 0; i < 10; i++) {
            final int j = i;
            tpe.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(j);
                }
            });

        }
//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        tpe.shutdown();
    }
}

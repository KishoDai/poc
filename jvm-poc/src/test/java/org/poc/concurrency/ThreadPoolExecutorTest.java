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
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2,
                10,
                1L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2) {

                    @Override
                    public boolean offer(Runnable runnable) {
                        System.out.println("offer runnable to queue=>" + runnable);
                        return super.offer(runnable);
                    }

                    @Override
                    public void put(Runnable runnable) throws InterruptedException {
                        System.out.println("put runnable to queue=>" + runnable);
                        super.put(runnable);
                    }

                    @Override
                    public boolean add(Runnable runnable) {
                        System.out.println("add runnable to queue=>" + runnable);
                        return super.add(runnable);
                    }
                },
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        System.out.println("create new Runnable:" + r);
                        return new Thread(r);
                    }
                },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("reject r=>" + r);
                    }
                });

        for (int i = 0; i < 10; i++) {
            final int j = i;
            Future future = tpe.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(j);
                }
            });

        }
        System.out.println("queue size->" + tpe.getQueue().size());
//        System.out.println("poolSize=" + tpe.getPoolSize());
//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("poolSize=" + tpe.getPoolSize());

        tpe.shutdown();
    }
}

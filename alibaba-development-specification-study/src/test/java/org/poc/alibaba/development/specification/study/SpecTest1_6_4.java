package org.poc.alibaba.development.specification.study;

import java.util.concurrent.*;

public class SpecTest1_6_4 {

    public static void main(String[] args) {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(4,
                10,
                10L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(4) {
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
                    public Thread newThread(Runnable r) {
                        System.out.println("create new Runnable:" + r);
                        return new Thread(r);
                    }
                },
                new RejectedExecutionHandler() {
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("reject r=>" + r);
                    }
                });

        for (int i = 0; i < 10; i++) {
            final int j = i;
            Future future = tpe.submit(new Runnable() {
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
        tpe.shutdown();
    }
}


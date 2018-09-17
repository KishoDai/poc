package org.poc.concurrency;

import java.util.concurrent.*;

public class UncaughtExceptionHandlerTest {

    public static void main(String[] args) {
        setMyUncaughtExceptionHandlerToThread();
        rewriteThreadPoolExecutor();
        rewriteThreadPoolExecutor2();

    }

    private static void rewriteThreadPoolExecutor() {
        class MyThreadPoolExecutor extends ThreadPoolExecutor {

            public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
                super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
            }

            public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
                super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
            }

            public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
                super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
            }

            public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
                super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                t.printStackTrace();
                super.afterExecute(r, t);
            }
        }

        MyThreadPoolExecutor mtpe = new MyThreadPoolExecutor(1, 1, 100, TimeUnit.SECONDS, new ArrayBlockingQueue(10),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("reject " + r);
                    }
                });

        mtpe.execute(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("rewriteThreadPoolExecutor");
            }
        });
        mtpe.shutdown();
    }

    private static void rewriteThreadPoolExecutor2() {
        class MyThreadPoolExecutor extends ThreadPoolExecutor {

            public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
                super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
            }

            public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
                super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
            }

            public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
                super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
            }

            public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
                super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
            }


        }

        MyThreadPoolExecutor mtpe = new MyThreadPoolExecutor(1, 1, 100, TimeUnit.SECONDS, new ArrayBlockingQueue(10),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("reject " + r);
                    }
                });

        mtpe.setThreadFactory(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        System.out.println("hhi=>" + e.getCause());
                    }
                });
                return t;
            }
        });

        mtpe.execute(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("rewriteThreadPoolExecutor2");
            }
        });
        mtpe.shutdown();
    }

    private static void setMyUncaughtExceptionHandlerToThread() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("setMyUncaughtExceptionHandlerToThread");
            }
        });
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        t.start();
    }

}


class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Thread->" + t + ", Throwable->" + e);
    }

}
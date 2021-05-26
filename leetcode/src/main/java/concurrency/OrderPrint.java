package concurrency;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;

public class OrderPrint {

    private static volatile int v = 0;

    public static void main(String[] args) {
        String[] printStrArr = new String[]{"A1", "B2", "C3"};
        int count = 3;
        Semaphore s = new Semaphore(1);
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++) {
            executorService.submit(() -> {
                boolean lock = false;
                try {
                    s.acquire();
                    lock = true;
                    System.out.print(printStrArr[v++]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (lock) {
                        s.release();
                    }
                }
            });
        }
        executorService.submit(new FutureTask(() -> {
            System.out.println(Thread.currentThread());
            System.out.println("call");
            return null;
        }));
        executorService.shutdown();
        System.out.println();
    }
}
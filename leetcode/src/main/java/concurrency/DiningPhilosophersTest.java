package concurrency;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DiningPhilosophersTest {

    @Test
    public void testDiningPhilosophers() throws InterruptedException {
        run(new DiningPhilosophers());
    }


    private void run(DiningPhilosophers diningPhilosophers) throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        int n = 5;
        List<Future> futures = new ArrayList<Future>(5);
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {

        }
        futures.add(es.submit(() -> {
        }));

        for (Future future : futures) {
            try {
                future.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        System.out.println("Use time : " + (System.currentTimeMillis() - start) + "ms!");
        es.shutdown();
    }
}

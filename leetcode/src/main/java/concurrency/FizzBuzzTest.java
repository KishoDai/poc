package concurrency;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FizzBuzzTest {

    @Test
    public void testFizzBuzz() throws InterruptedException {
        run(new FizzBuzz(15));
    }

    @Test
    public void testFizzBuzz2() throws InterruptedException {
        run(new FizzBuzz2(15));
    }

    @Test
    public void testFizzBuzz3() throws InterruptedException {
        run(new FizzBuzz3(15));
    }

    private void run(FizzBuzz fizzBuzz) throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        List<Future> futures = new ArrayList<Future>(4);
        long start = System.currentTimeMillis();
        futures.add(es.submit(() -> {
            try {
                fizzBuzz.fizz(() -> {
                    System.out.print("fizz,");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        futures.add(es.submit(() -> {
            try {
                fizzBuzz.buzz(() -> {
                    System.out.print("buzz,");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        futures.add(es.submit(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> {
                    System.out.print("fizzbuzz,");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        futures.add(es.submit(() -> {
            try {
                fizzBuzz.number(new IntConsumer());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

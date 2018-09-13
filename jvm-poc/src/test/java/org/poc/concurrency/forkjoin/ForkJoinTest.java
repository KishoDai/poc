package org.poc.concurrency.forkjoin;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {

    private long max = 10000000000L;

    @Test
    public void testWithSingleThread() {
        long startTime = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i <= max; i++) {
            sum += i;
        }

        long endTime = System.currentTimeMillis();
        System.out.println("sum : " + sum);
        System.out.println("used time : " + (endTime - startTime));
    }

    @Test
    public void testWithManyThread() {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinCalculator calculator = new ForkJoinCalculator(0, max);
        long startTime = System.currentTimeMillis();
        long sum = pool.invoke(calculator);
        long endTime = System.currentTimeMillis();
        System.out.println("sum : " + sum);
        System.out.println("used time : " + (endTime - startTime));
    }


    class ForkJoinCalculator extends RecursiveTask<Long> {

        private static final long THRESOLD = 10000L;

        private long start;
        private long end;

        public ForkJoinCalculator(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= THRESOLD) {
                long sum = 0;
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
                System.out.println("sub task sum : " + sum);
                return sum;
            }

            long mid = (start + end) / 2;
            ForkJoinCalculator calculator1 = new ForkJoinCalculator(start, mid);
            calculator1.fork();

            ForkJoinCalculator calculator2 = new ForkJoinCalculator(mid + 1, end);
            calculator2.fork();

            return (Long) calculator1.join() + (Long) calculator2.join();
        }

    }
}

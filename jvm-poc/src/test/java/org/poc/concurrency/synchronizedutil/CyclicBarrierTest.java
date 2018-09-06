package org.poc.concurrency.synchronizedutil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    final int N;
    final float[][] data;
    final CyclicBarrier barrier;

    class Worker implements Runnable {
        int myRow;

        Worker(int row) {
            myRow = row;
        }

        public void run() {
//            while (!done()) {
//                processRow(myRow);
            System.out.println("row=" + myRow);
            try {
                barrier.await();
            } catch (InterruptedException ex) {
                return;
            } catch (BrokenBarrierException ex) {
                return;
            }
//            }
        }
    }

    public CyclicBarrierTest(float[][] matrix) {
        data = matrix;
        N = matrix.length;
        Runnable barrierAction =
                new Runnable() {
                    public void run() {
                        System.out.println("xxx");
                    }
                };
        barrier = new CyclicBarrier(N, barrierAction);
        List<Thread> threads = new ArrayList<Thread>(N);
        for (int i = 0; i < N; i++) {
            Thread thread = new Thread(new Worker(i));
            threads.add(thread);
            thread.start();
        }
        // wait until done
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrierTest test = new CyclicBarrierTest(new float[][]{{1, 2}, {3, 4}});
    }
}

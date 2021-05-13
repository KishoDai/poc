package concurrency;

//编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
//
//
// 如果这个数字可以被 3 整除，输出 "fizz"。
// 如果这个数字可以被 5 整除，输出 "buzz"。
// 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
//
//
// 例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14
//, fizzbuzz。
//
// 假设有这么一个类：
//
//
//class FizzBuzz {
//  public FizzBuzz(int n) { ... }               // constructor
//  public void fizz(printFizz) { ... }          // only output "fizz"
//  public void buzz(printBuzz) { ... }          // only output "buzz"
//  public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
//  public void number(printNumber) { ... }      // only output the numbers
//}
//
// 请你实现一个有四个线程的多线程版 FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：
//
//
// 线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
// 线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
// 线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
// 线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。
//
//
//
//
// 提示：
//
//
// 本题已经提供了打印字符串的相关方法，如 printFizz() 等，具体方法名请参考答题模板中的注释部分。
//
//
//
// 👍 49 👎 0


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

//leetcode submit region begin(Prohibit modification and deletion)
class FizzBuzz {
    private int n;

    private static final int ZERO = 0;
    private static final int BOMB = -1;

    private SynchronousQueue<Integer> controlQ = new SynchronousQueue();
    private SynchronousQueue<Integer> fizzQ = new SynchronousQueue();
    private SynchronousQueue<Integer> buzzQ = new SynchronousQueue();
    private SynchronousQueue<Integer> fizzbuzzQ = new SynchronousQueue();

    //1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14
    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (true) {
            Integer v = fizzQ.take();
            if (v == BOMB) {
                return;
            }
            printFizz.run();
            controlQ.put(ZERO);
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (true) {
            Integer v = buzzQ.take();
            if (v == BOMB) {
                return;
            }
            printBuzz.run();
            controlQ.put(ZERO);
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (true) {
            Integer v = fizzbuzzQ.take();
            if (v == BOMB) {
                return;
            }
            printFizzBuzz.run();
            controlQ.put(ZERO);
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            boolean divide3 = i % 3 == 0;
            boolean divide5 = i % 5 == 0;
            if (!divide3 && !divide5) {
                printNumber.accept(i);
                continue;
            } else if (divide3 && divide5) {
                fizzbuzzQ.put(ZERO);
            } else if (divide3 && !divide5) {
                fizzQ.put(ZERO);
            } else if (!divide3 && divide5) {
                buzzQ.put(ZERO);
            }
            controlQ.take();
        }
        fizzbuzzQ.put(BOMB);
        fizzQ.put(BOMB);
        buzzQ.put(BOMB);
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        List<Future> futures = new ArrayList<Future>(4);
        FizzBuzz fizzBuzz = new FizzBuzz(15);
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
        es.shutdown();
    }
}

//leetcode submit region end(Prohibit modification and deletion)


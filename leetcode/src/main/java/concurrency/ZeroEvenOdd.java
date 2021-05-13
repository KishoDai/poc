package concurrency;
//假设有这么一个类：
//
// class ZeroEvenOdd {
//  public ZeroEvenOdd(int n) { ... }      // 构造函数
//  public void zero(printNumber) { ... }  // 仅打印出 0
//  public void even(printNumber) { ... }  // 仅打印出 偶数
//  public void odd(printNumber) { ... }   // 仅打印出 奇数
//}
//
//
// 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：
//
//
// 线程 A 将调用 zero()，它只输出 0 。
// 线程 B 将调用 even()，它只输出偶数。
// 线程 C 将调用 odd()，它只输出奇数。
//
//
// 每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n
//。
//
//
//
// 示例 1：
//
// 输入：n = 2
//输出："0102"
//说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
//
//
// 示例 2：
//
// 输入：n = 5
//输出："0102030405"
//
// 👍 97 👎 0


import java.util.concurrent.SynchronousQueue;

//leetcode submit region begin(Prohibit modification and deletion)
public class ZeroEvenOdd {

    private static final int ZERO = 0;
    private static final int BOMB = 0;

    private int n;

    private SynchronousQueue<Integer> zeroQ = new SynchronousQueue();
    private SynchronousQueue<Integer> evenQ = new SynchronousQueue();
    private SynchronousQueue<Integer> oddQ = new SynchronousQueue();

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            printNumber.accept(ZERO);
            if ((i & 1) == 1) {
                oddQ.put(i);
            } else {
                evenQ.put(i);
            }
            zeroQ.take();
        }
        oddQ.put(BOMB);
        evenQ.put(BOMB);
    }

    //偶数
    public void even(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            int i = evenQ.take();
            if (i == BOMB) {
                return;
            }
            printNumber.accept(i);
            zeroQ.put(ZERO);
        }
    }

    //奇数
    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            int i = oddQ.take();
            if (i == BOMB) {
                return;
            }
            printNumber.accept(i);
            zeroQ.put(ZERO);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final IntConsumer printNumber = new IntConsumer();
        final ZeroEvenOdd target = new ZeroEvenOdd(10);
        Thread zeroThread = new Thread(() -> {
            try {
                target.zero(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread oddThread = new Thread(() -> {
            try {
                target.odd(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread evenThread = new Thread(() -> {
            try {
                target.even(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        zeroThread.start();
        oddThread.start();
        evenThread.start();
        zeroThread.join();
        oddThread.join();
        evenThread.join();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
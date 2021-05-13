package concurrency;

public class Foo {

    private volatile byte count = 0;

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        count = 1;
    }


    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        while (count == 0) {
            Thread.yield();
        }
        printSecond.run();
        count = 2;
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        while (count <= 1) {
            Thread.yield();
        }
        printThird.run();
    }
}
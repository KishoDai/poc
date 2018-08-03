package org.poc;

public class PossibleReorderingTest {
    static int x = 0;
    static int y = 0;
    static int a = 0;
    static int b = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(new Runnable() {
            public void run() {
                a = 1;
                x = b;
            }
        });

        Thread two = new Thread(new Runnable() {
            public void run() {
                b = 1;
                y = a;
            }
        });

        one.start();
        two.start();

        one.join();
        two.join();
        System.out.println("(" + x + "," + y + ")");
    }
}

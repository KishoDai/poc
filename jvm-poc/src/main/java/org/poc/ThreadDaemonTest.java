package org.poc;

public class ThreadDaemonTest {

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("You got me finally!");
                }
            }
        });

//        t.setDaemon(true);
        t.start();

        System.out.println("over");
    }
}

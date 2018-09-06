package org.poc;

public class JvmHookTest {

    public static void main(String[] args) throws InterruptedException {

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("You are shutdown JVM!!!");
            }
        }));
        System.exit(1);

        Thread.sleep(1000);
        System.out.println("Normally finished!");
    }
}

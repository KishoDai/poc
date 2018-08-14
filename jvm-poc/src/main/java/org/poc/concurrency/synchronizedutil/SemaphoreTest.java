package org.poc.concurrency.synchronizedutil;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(10);
        semaphore.acquire(10);
        System.out.println("xxxx");
        semaphore.release(10);
    }

}

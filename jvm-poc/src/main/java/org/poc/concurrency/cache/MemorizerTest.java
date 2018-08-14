package org.poc.concurrency.cache;

import java.math.BigInteger;

public class MemorizerTest {

    public static void main(String[] args) throws InterruptedException {
        ExpensiveFunction function = new ExpensiveFunction();
        Memorizer<String, BigInteger> memorizer = new Memorizer<>(function);
        System.out.println(memorizer.compute("1111"));
    }

}

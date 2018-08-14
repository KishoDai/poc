package org.poc.concurrency.cache;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        //在经过长时间运算后
        return new BigInteger(arg);
    }
}

package org.poc.concurrency.lock;

import org.junit.Test;

import java.util.List;
import java.util.Vector;

public class BiasedLockTest {

    /**
     * -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0 -client -Xmx512m -Xms512m
     */
    @Test
    public void testUseBiasedLocking() {
        long begin = System.currentTimeMillis();
        List<Integer> numberList = new Vector<Integer>();
        int count = 0;
        int startNum = 0;
        while (count++ < 10000000) {
            numberList.add(startNum);
            startNum += 2;
        }
        System.out.println(System.currentTimeMillis() - begin);
        //启用偏向锁：耗时200ms左右
    }
}

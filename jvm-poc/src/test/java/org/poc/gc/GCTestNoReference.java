package org.poc.gc;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class GCTestNoReference {

    @Test
    public void test() {
        int i = 1000000;

        while (i-- > 0) {
            Object obj = new Object() {
                @Override
                public void finalize() {
                    System.out.println("finalize()");
                }
            };
        }
        System.gc();
        System.runFinalization();
    }
}

package org.poc.jvm;

import org.junit.Test;

import java.util.Date;

public class IntegerTest {


    @Test
    public void test() {
//        System.out.println(Integer.valueOf(-128) == Integer.valueOf(-128));
        System.out.println(Integer.valueOf(127) == Integer.valueOf(127));
        System.out.println(Integer.valueOf(128) == Integer.valueOf(128));
        System.out.println(Integer.valueOf(128).intValue() == Integer.valueOf(128).intValue());
        System.out.println(Integer.parseInt("127") == Integer.valueOf(127));
        Integer i = 129;
        Integer j = 129;
        System.out.println(i == j);
        Integer m = 127;
        Integer n = 127;
        System.out.println(m == n);
        long l = 1531818346268L;
        System.out.println(new Date(l));
    }
}

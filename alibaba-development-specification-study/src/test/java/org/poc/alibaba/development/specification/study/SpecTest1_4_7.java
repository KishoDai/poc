package org.poc.alibaba.development.specification.study;

import org.junit.Assert;
import org.junit.Test;

public class SpecTest1_4_7 {


    @Test
    public void test() {
        Assert.assertTrue(Integer.valueOf(127) == Integer.valueOf(127));
        Assert.assertTrue(Integer.valueOf(128) != Integer.valueOf(128));
        Assert.assertTrue(Integer.valueOf(128).intValue() == Integer.valueOf(128).intValue());
        Assert.assertTrue(Integer.parseInt("127") == Integer.valueOf(127));
        Integer i = 129;
        Integer j = 129;
        Assert.assertTrue(i != j);
        Integer m = 127;
        Integer n = 127;
        Assert.assertTrue(m == n);
    }
}

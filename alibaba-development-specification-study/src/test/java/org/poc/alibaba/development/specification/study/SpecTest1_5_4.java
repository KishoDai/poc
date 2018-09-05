package org.poc.alibaba.development.specification.study;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SpecTest1_5_4 {

    @Test
    public void testToArray() {
        List<String> list = new ArrayList<String>(2);
        list.add("guan");
        list.add("bao");

        String[] array1 = new String[list.size()];
        list.toArray(array1);

        Assert.assertFalse(list.toArray() instanceof String[]);
        Assert.assertTrue(list.toArray() instanceof Object[]);
    }
}

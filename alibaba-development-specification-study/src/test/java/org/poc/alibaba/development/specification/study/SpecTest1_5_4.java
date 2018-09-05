package org.poc.alibaba.development.specification.study;

import org.junit.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SpecTest1_5_4 {

    @Test(expected = ClassCastException.class)
    public void testToArray() {
        List<String> list = new ArrayList<String>(2);
        list.add("guan");
        list.add("bao");

        String[] array1 = new String[list.size()];
        list.toArray(array1);

        System.out.println("array1[0] = " + array1[0]);
        String[] array2 = (String[]) list.toArray();
    }
}

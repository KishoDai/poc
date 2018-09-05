package org.poc.alibaba.development.specification.study;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SpecTest1_5_2 {

    @Test
    public void testAddOnSubList() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");

        List<String> subList = list.subList(1, 2);
        subList.add("4");

        System.out.println("list = " + list);
        System.out.println("subList = " + subList);

        Assert.assertEquals(4, list.size());
        Assert.assertEquals(2, subList.size());
    }

    @Test(expected = ClassCastException.class)
    public void testClassCastException() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");

        List<String> subList = list.subList(1, 2);
        System.out.println("list = " + list);
        System.out.println("subList = " + subList);

        ArrayList<String> subList1 = (ArrayList<String>) subList;
    }
}

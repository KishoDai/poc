package org.poc.alibaba.development.specification.study;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpecTest1_5_7 {

    @Test
    public void testRemoveItemFromList() {
        List<String> list = new ArrayList();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("2".equals(item)) {
                iterator.remove();
            }
        }
    }

    @Test
    public void testRemoveItemFromList2() {
        List<String> list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        for (String item : list) {
            if ("4".equals(item)) {
                list.remove(item);
            }
            System.out.println(item);
        }
        System.out.println("list = " + list);
    }

}


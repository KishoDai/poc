package org.poc.alibaba.development.specification.study;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SpecTest1_5_6 {

    @Test
    public void testExtends() {
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        List<? extends Self> selfList = list;
//        selfList.add(new Self());
//        selfList.add(new Son());
        selfList.add(null);
        for (Object obj : selfList) {
            System.out.println(obj);
        }
        System.out.println();

        selfList = new ArrayList<Self>() {
            {
                add(new Self());
                add(new Son());
            }
        };
        for (Self self : selfList) {
            System.out.println(self);
        }
        System.out.println();

        selfList = new ArrayList<Son>() {
            {
                add(new Son());
            }
        };
        for (Self self : selfList) {
            System.out.println(self);
        }
        System.out.println();
    }

    @Test
    public void testSuper() {
        List<? super Self> selfList = new ArrayList() {
                {
//                    add("1");
//                    add("2");
                }
        };
        selfList.add(new Self());
        selfList.add(new Son());
//        selfList.add(null);
        for (Object obj : selfList) {
            System.out.println(obj);
        }
        System.out.println();

        selfList = new ArrayList<Self>() {
            {
                add(new Self());
                add(new Son());
            }
        };
        for (Object obj : selfList) {
            System.out.println(obj);
        }
        System.out.println();

        selfList = new ArrayList<Father>() {
            {
                add(new Son());
                add(new Self());
                add(new Father());
            }
        };
        for (Object obj : selfList) {
            System.out.println(obj);
        }
        System.out.println();
    }

}

class Father {

}

class Self extends Father {

}

class Son extends Self {

}


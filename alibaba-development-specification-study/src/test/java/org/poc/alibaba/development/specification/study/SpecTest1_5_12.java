package org.poc.alibaba.development.specification.study;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SpecTest1_5_12 {


    @Test(expected = NullPointerException.class)
    public void testKeyIsNull4Hashtable() {
        Map<String, String> map = new Hashtable<String, String>();
        map.put(null, "1");
    }

    @Test(expected = NullPointerException.class)
    public void testValueIsNull4Hashtable() {
        Map<String, String> map = new Hashtable<String, String>();
        map.put("1", null);
    }

    @Test(expected = NullPointerException.class)
    public void testKeyIsNull4ConcurrentHashMap() {
        Map<String, String> map = new ConcurrentHashMap<String, String>();
        map.put(null, "1");
    }

    @Test(expected = NullPointerException.class)
    public void testValueIsNull4ConcurrentHashMap() {
        Map<String, String> map = new ConcurrentHashMap<String, String>();
        map.put("1", null);
    }

    @Test(expected = NullPointerException.class)
    public void testKeyIsNull4TreeMap() {
        Map<String, String> map = new TreeMap<String, String>();
        map.put(null, "1");
    }
}


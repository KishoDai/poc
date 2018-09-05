package org.poc.alibaba.development.specification.study;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpecTest1_5_5 {

    @Test(expected = UnsupportedOperationException.class)
    public void testAsList() {
        String[] str = new String[]{"you", "wu"};
        List<String> list = Arrays.asList(str);
        list.add("yangguanbao");
    }

    @Test
    public void testModification() {
        String[] str = new String[]{"you", "wu"};
        List<String> list = Arrays.asList(str);

        str[0] = "gujin";
        Assert.assertEquals(str[0], list.get(0));
    }
}

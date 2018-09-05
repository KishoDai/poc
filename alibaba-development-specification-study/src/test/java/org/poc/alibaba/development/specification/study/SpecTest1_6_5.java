package org.poc.alibaba.development.specification.study;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SpecTest1_6_5 {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd");
        System.out.println(sdf.format(new Date()));

        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));
    }
}


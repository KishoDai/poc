package org.poc;

import java.util.Calendar;
import java.util.Date;

public class SplitTest {
    public static void main(String[] args) {
//        String str = "a,b,c,,";
//        String[] ary = str.split(",");
// 预期大于 3，结果是 3
//        System.out.println(ary.length);
//        System.out.println(Math.pow(2,16));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        System.out.println(calendar.getTime().getTime());
    }
}

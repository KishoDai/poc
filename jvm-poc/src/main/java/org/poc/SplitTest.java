package org.poc;

public class SplitTest {
    public static void main(String[] args) {
        String str = "a,b,c,,";
        String[] ary = str.split(",");
// 预期大于 3，结果是 3
        System.out.println(ary.length);
    }
}

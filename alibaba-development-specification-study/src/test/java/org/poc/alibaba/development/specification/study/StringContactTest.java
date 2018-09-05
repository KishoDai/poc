package org.poc.alibaba.development.specification.study;

public class StringContactTest {
    public static void main(String[] args) {
        String str = "start";
        for (int i = 0; i < 100; i++) {
            str = str + "hello";
        }
    }
}

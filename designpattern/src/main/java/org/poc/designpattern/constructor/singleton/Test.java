package org.poc.designpattern.constructor.singleton;

public class Test {
    //-XX:+PrintCompilation
    public static void main(String[] args) {
        Singleton3 instance = Singleton3.getInstance();
//        for (int i = 0; i < 1000000; i++) {
//            ;
//        }
//        System.out.println("instance=" + instance);
    }
}

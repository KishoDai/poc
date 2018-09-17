package org.poc.alibaba.development.specification.study;

public class SpecTest1_6_1 {

}

//饿汉式
class Singleton1 {

    private static Singleton1 INSTANCE = new Singleton1();

    public static Integer I = new Integer(1);

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}

//懒汉式：延迟加载类占位模式
class Singleton3 {

    private static Singleton3 INSTANCE = null;

    public static Integer I = new Integer(1);

    private Singleton3() {
    }

    private static class ResourceHolder {
        private static Singleton3 INSTANCE = new Singleton3();
    }

    public static Singleton3 getInstance() {
        return ResourceHolder.INSTANCE;
    }

}

//懒汉式：双重检查锁
class Singleton2 {

    private static Singleton2 INSTANCE = null;

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton2.class) {
                if (INSTANCE == null) {
                    return INSTANCE = new Singleton2();
                }
            }
        }
        return INSTANCE;
    }
}


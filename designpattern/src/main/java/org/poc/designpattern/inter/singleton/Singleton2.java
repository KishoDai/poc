package org.poc.designpattern.inter.singleton;

//懒汉式：双重检查锁
public class Singleton2 {

    private static Singleton2 INSTANCE = null;

    private Singleton2() {
        throw new Error("Can not invoke constructor!");
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

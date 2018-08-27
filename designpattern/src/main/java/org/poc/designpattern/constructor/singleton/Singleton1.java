package org.poc.designpattern.constructor.singleton;

//饿汉式
public class Singleton1 {

    private static Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {
        throw new Error("Can not invoke constructor!");
    }

    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}

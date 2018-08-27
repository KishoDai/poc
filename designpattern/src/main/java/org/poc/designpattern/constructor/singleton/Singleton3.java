package org.poc.designpattern.constructor.singleton;

//懒汉式：延迟加载类占位模式
public class Singleton3 {

    private static Singleton3 INSTANCE = null;

    private Singleton3() {
        throw new Error("Can not invoke constructor!");
    }

    private static class ResourceHolder {
        private static Singleton3 INSTANCE = new Singleton3();
    }

    public static Singleton3 getInstance() {
        return ResourceHolder.INSTANCE;
    }

}

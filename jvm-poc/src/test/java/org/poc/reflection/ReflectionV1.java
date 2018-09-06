package org.poc.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionV1 {

    public static void target(int i) {
        new Exception("#" + i).printStackTrace();
    }

    public static void main(String[] arg) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> klass = Class.forName("org.poc.reflection.ReflectionV1");
        Method method = klass.getMethod("target", int.class);
        System.out.println("method == method2 =>" + (method == klass.getMethod("target", int.class)));
        for (int i = 0; i < 20; i++) {
            long start = System.currentTimeMillis();
            method.invoke(null, i);
            System.out.println(i + " used time : " + (System.currentTimeMillis() - start) + "ms");
        }
    }


}

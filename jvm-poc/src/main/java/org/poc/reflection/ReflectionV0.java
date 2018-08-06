package org.poc.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionV0 {

    public static void target(int i) {
        new Exception("#" + i).printStackTrace();
    }

    public static void main(String[] arg) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> klass = Class.forName("org.poc.reflection.ReflectionV0");
        Method method = klass.getMethod("target", int.class);
        long start = System.currentTimeMillis();
        method.invoke(null, 0);
        System.out.println("used time : " + (System.currentTimeMillis() - start) + "ms");
    }


}

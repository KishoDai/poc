package org.poc.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionV3 {

    public static void target(int i) {
    }

    public static void main(String[] arg) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> klass = Class.forName("org.poc.reflection.ReflectionV3");
        Method method = klass.getMethod("target", int.class);
        long current = System.currentTimeMillis();
        Integer pI = 128;
        Object[] pArr = new Object[1];
        pArr[0] = pI;
        for (int i = 0; i < 2000000000; i++) {
            if (i % 100000000 == 0) {
                long temp = System.currentTimeMillis();
                System.out.println(temp - current);
                current = temp;
            }
            method.invoke(null, pArr);
        }
    }


}
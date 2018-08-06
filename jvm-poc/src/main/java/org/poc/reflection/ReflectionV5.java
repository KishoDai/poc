package org.poc.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionV5 {

    public static void target(int i) {
    }

    //-XX:+PrintGC -Dsun.reflect.noInflation=true -XX:TypeProfileWidth=3
    public static void main(String[] arg) throws Exception {
        Class<?> klass = Class.forName("org.poc.reflection.ReflectionV5");
        Method method = klass.getMethod("target", int.class);
        method.setAccessible(true);
        polluteProfile();


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

    public static void polluteProfile() throws Exception {
        Method target1 = ReflectionV5.class.getMethod("target1", int.class);
        Method target2 = ReflectionV5.class.getMethod("target2", int.class);

        for (int i = 0; i < 2000; i++) {
            target1.invoke(null, 0);
            target2.invoke(null, 0);
        }
    }

    public static void target1(int i) {
    }

    public static void target2(int i) {
    }

}

package org.poc.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NoReflectionV2 {

    public static void target(int i) {
    }

    public static void main(String[] arg) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        long current = System.currentTimeMillis();
        for (int i = 0; i < 2000000000; i++) {
            if (i % 100000000 == 0) {
                long temp = System.currentTimeMillis();
                System.out.println(temp - current);
                current = temp;
            }
            target(i);
        }
    }


}

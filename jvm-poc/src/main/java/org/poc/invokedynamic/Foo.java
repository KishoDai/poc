package org.poc.invokedynamic;

import java.lang.invoke.MethodHandles;

public class Foo {

    private static void test(Object obj) {
        System.out.println("test(Object obj)");
    }

    public static MethodHandles.Lookup getLookup() {
        return MethodHandles.lookup();
    }

}

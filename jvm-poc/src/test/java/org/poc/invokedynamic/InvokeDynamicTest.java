package org.poc.invokedynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

public class InvokeDynamicTest {

    public static void main(String[] args) throws Throwable {
        //invokedynamic底层实现的基石：方法名柄
        MethodType methodType = MethodType.methodType(void.class, Object.class);
        MethodHandle methodHandle = Foo.getLookup().findStatic(Foo.class, "test", methodType);

        methodHandle.invoke(1);
    }

}

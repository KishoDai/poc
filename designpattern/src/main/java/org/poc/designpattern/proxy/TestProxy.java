package org.poc.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TestProxy implements InvocationHandler {

    private Object target;

    public TestProxy(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("test".equals(method.getName())) {
            System.out.println("before execute test()");
        }
        Object result = method.invoke(target, args);
        if ("test".equals(method.getName())) {
            System.out.println("after execute test()");
        }
        return result;
    }
}

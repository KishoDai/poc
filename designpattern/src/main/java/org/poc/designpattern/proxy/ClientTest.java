package org.poc.designpattern.proxy;

import java.lang.reflect.Proxy;

public class ClientTest {

    public static void main(String[] args) {
        TestImpl testImpl = new TestImpl();
        TestProxy proxy = new TestProxy(testImpl);
        ITest test = (ITest) Proxy.newProxyInstance(TestProxy.class.getClassLoader(),
                new Class[]{ITest.class},
                proxy);
        test.test();
    }

}

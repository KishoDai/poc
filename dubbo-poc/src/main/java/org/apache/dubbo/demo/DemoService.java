package org.apache.dubbo.demo;

public interface DemoService {
    String sayHello(String name);

    String sayHello(String name, int sleepMillis);
}
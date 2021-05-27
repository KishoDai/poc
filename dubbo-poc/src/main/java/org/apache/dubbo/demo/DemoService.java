package org.apache.dubbo.demo;

import javax.validation.constraints.NotNull;

public interface DemoService {
    String sayHello(@NotNull String name);

    String sayHello(@NotNull String name, int sleepMillis);
}
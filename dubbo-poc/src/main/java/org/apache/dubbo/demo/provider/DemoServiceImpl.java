package org.apache.dubbo.demo.provider;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.demo.DemoService;

@Slf4j
public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = "server : Hello " + name;
        log.info(result);
        return result;
    }
}
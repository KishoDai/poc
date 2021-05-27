package org.apache.dubbo.demo.provider;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.demo.DemoService;

@Slf4j
public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        return sayHello(name, 0);
    }

    public String sayHello(String name, int sleepMillis) {
        long start = System.currentTimeMillis();
        if (sleepMillis > 0) {
            try {
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String result = "Hello " + name;
        log.info("server : {}, processed time : {}ms", result, (System.currentTimeMillis() - start));
        return result;
    }
}
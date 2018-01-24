package org.poc.springcloud.config.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class SpringCloudConfigClientApplication {

    @Value('${foo}')
    private String foo

    @RequestMapping('hi')
    String hi() {
        return foo
    }

    static void main(args) {
        SpringApplication.run(SpringCloudConfigClientApplication.class, args)
    }

}

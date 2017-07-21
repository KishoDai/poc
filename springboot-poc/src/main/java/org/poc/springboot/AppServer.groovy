package org.poc.springboot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ImportResource

/**
 * Created by Administrator on 2017/7/21 0021.
 */
@SpringBootApplication
@ImportResource('classpath*:application-springboot-poc.xml')
class AppServer {

    static void main(args) {
        SpringApplication.run(AppServer.class, args)
    }

}

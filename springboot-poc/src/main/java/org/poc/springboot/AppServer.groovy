package org.poc.springboot

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ImportResource
/**
 * Created by Administrator on 2017/7/21 0021.
 */
@SpringBootApplication
@ImportResource('classpath*:application-springboot-poc.xml')
class AppServer {
    private static final Logger LOG = LoggerFactory.getLogger(AppServer.class)

    static void main(args) {
        SpringApplication.run(AppServer.class, args)
        LOG.info('hixxxxxxxxxxxxxxxxxxxx')
    }

}

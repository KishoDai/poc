package org.poc.springcloud.config.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.config.server.EnableConfigServer
import org.springframework.context.annotation.Configuration

/**
 * Created by kisho on 2018/1/24.
 */
@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableConfigServer
class SpringCloudConfigServerApplication {

    static void main(args) {
        SpringApplication.run(SpringCloudConfigServerApplication.class, args)
    }

}
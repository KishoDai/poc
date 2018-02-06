package org.poc.neo4jpoc

import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

class PocEtlMain {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PocEtlMain.class)

    static void main(args) {
        ApplicationContext context
        try {
            context = new ClassPathXmlApplicationContext('classpath:applicationContext.xml')
            context.getBean(PocEtlService.class).start()
        } catch (Throwable e) {
            LOG.error('', e)
        } finally {
            if (context != null) {
                context.close()
            }
        }
    }

}
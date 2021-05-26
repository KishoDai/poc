package dubbo;

import org.apache.dubbo.demo.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsumerStarter {
    private static Logger LOG = LoggerFactory.getLogger(ConsumerStarter.class);

    public static void start(String consumerXml) {
        start(consumerXml, 1);
    }

    public static void start(String consumerXml, int count) {
        ExecutorService es = Executors.newFixedThreadPool(count);
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(consumerXml);
        context.start();
        final DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
        for (int i = 0; i < count; i++) {
            es.submit(new Runnable() {
                public void run() {
                    LOG.info("client : {}", demoService.sayHello("world")); // 执行远程方法
                    countDownLatch.countDown();
                }
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        es.shutdown();
    }

}
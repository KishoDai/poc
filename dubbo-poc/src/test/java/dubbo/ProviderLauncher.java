package dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProviderLauncher {
    private static Logger LOG = LoggerFactory.getLogger(ProviderLauncher.class);

    public static void launch(String... providerXMLs) {
        ExecutorService es = Executors.newFixedThreadPool(providerXMLs.length);
        final CountDownLatch countDownLatch = new CountDownLatch(providerXMLs.length);
        for (final String providerXML : providerXMLs) {
            es.submit(new Runnable() {
                public void run() {
                    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(providerXML);
                    context.start();
                    countDownLatch.countDown();
                    block();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOG.info("provider started : {}", providerXMLs.length);
    }

    public static void block() {
        try {
            System.in.read(); // 按任意键退出
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
package dubbo;

import org.apache.dubbo.demo.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ConsumerLauncher {
    private static Logger LOG = LoggerFactory.getLogger(ConsumerLauncher.class);

    public static void consumer(String consumerXml) {
        consumer(consumerXml, 0);
    }

    public static void consumer(String consumerXml, int serverProcessMillis) {
        consumer(consumerXml, "world", 1, serverProcessMillis);
    }

    public static void consumer(String consumerXml, String name) {
        consumer(consumerXml, name, 1, 0);
    }

    public static void consumer(String consumerXml, final String name, int count, final int serverProcessMillis) {
        ExecutorService es = Executors.newFixedThreadPool(count);
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(consumerXml);
        context.start();
        final DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
        List<Future> futures = new ArrayList<Future>(count);
        for (int i = 0; i < count; i++) {
            futures.add(es.submit(new Callable<Exception>() {
                public Exception call() {
                    try {
                        long start = System.currentTimeMillis();
                        String result = demoService.sayHello(name, serverProcessMillis);
                        LOG.info("client : {}, use time : {}ms", result, (System.currentTimeMillis() - start)); // 执行远程方法
                    } catch (Exception e) {
                        return e;
                    } finally {
                        countDownLatch.countDown();
                    }
                    return null;
                }
            }));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        es.shutdown();
        for (Future<Exception> future : futures) {
            try {
                Exception e = future.get();
                if (e != null) {
                    if (e instanceof RuntimeException) {
                        throw (RuntimeException) e;
                    } else {
                        throw new RuntimeException(e);
                    }
                }
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (ExecutionException executionException) {
                executionException.printStackTrace();
            }
        }
    }

}
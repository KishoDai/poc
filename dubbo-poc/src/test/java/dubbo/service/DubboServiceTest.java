package dubbo.service;


import org.junit.Test;

import static dubbo.ConsumerStarter.start;
import static dubbo.ProviderLauncher.launch;

public class DubboServiceTest {

    @Test
    public void testDubboServiceLoadBalance() {
        launch(
                "dubbo/service/loadbalance/provider.xml",
                "dubbo/service/loadbalance/provider2.xml");
        start("dubbo/service/loadbalance/consumer.xml", 3);
    }


}
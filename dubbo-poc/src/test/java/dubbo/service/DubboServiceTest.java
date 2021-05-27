package dubbo.service;


import org.junit.Test;

import static dubbo.ConsumerLauncher.consumer;
import static dubbo.ProviderLauncher.block;
import static dubbo.ProviderLauncher.provider;

public class DubboServiceTest {

    @Test
    public void testDubboServiceLoadBalance() {
        provider("dubbo/service/loadbalance/provider.xml",
                "dubbo/service/loadbalance/provider2.xml");
        consumer("dubbo/service/loadbalance/consumer.xml", 3);
    }

    @Test
    public void testDubboServiceGroup() {
        provider("dubbo/service/group/provider.xml");
        consumer("dubbo/service/group/consumer.xml");
    }

    @Test
    public void testDubboServiceTimeout4Provider() {
        provider("dubbo/service/timeout/provider.xml");
        block();
    }

    @Test(timeout = 4000)
    public void testDubboServiceTimeout4Consumer() {
        consumer("dubbo/service/timeout/consumer.xml", 500);
    }

}
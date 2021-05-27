package dubbo.consumer;


import org.junit.Test;

import javax.validation.ValidationException;

import static dubbo.ConsumerLauncher.consumer;
import static dubbo.ProviderLauncher.provider;

public class DubboConsumerTest {

    @Test
    public void testDubboConsumerCheck() throws InterruptedException {
//        consumer("dubbo/consumer/check/consumer.xml");
//        Thread.sleep(2000);
        provider("dubbo/consumer/check/provider.xml");
    }

    @Test(expected = ValidationException.class)
    public void testDubboConsumerValidation() throws InterruptedException {
        provider("dubbo/consumer/validation/provider.xml");
        consumer("dubbo/consumer/validation/consumer.xml", null);
    }


}
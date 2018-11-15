package cn.xiangme.fintech;

import cn.xiangme.fintech.capitalmgmt.MQChannelResponsibilityHandler;
import cn.xiangme.fintech.core.capitalmgmt.Context;
import cn.xiangme.fintech.core.capitalmgmt.DispatchResponsibilityHandler;
import cn.xiangme.fintech.core.capitalmgmt.ResponsibilityHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        MQChannelResponsibilityHandler handler = context.getBean(MQChannelResponsibilityHandler.class);
        handler.consume("I'm kisho!");
        System.out.println();
        System.out.println();

        ResponsibilityHandler handler2 = context.getBean(DispatchResponsibilityHandler.class);
        Context context2 = new Context("023", "123456789", "3000", "1111");
        handler2.handle(context2);
        System.out.println();
        System.out.println();

        handler2 = context.getBean(DispatchResponsibilityHandler.class);
        context2 = new Context("023", "123456789", "4000", "1111");
        handler2.handle(context2);
        System.out.println();
        System.out.println();

        handler2 = context.getBean(DispatchResponsibilityHandler.class);
        context2 = new Context("023", "123456789", "5000", "1111");
        handler2.handle(context2);
    }
}

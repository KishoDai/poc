package cn.xiangme.fintech;

import cn.xiangme.fintech.capitalmgmt.MQApplyApplyChannel;
import cn.xiangme.fintech.capitalmgmt.MQNotifyChannel;
import cn.xiangme.fintech.core.capitalmgmt.Context;
import cn.xiangme.fintech.core.capitalmgmt.DefaultHandlerDispatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        MQApplyApplyChannel channel = context.getBean(MQApplyApplyChannel.class);
        channel.consume("I'm kisho!");
        System.out.println();
        System.out.println();

        MQNotifyChannel channel2 = context.getBean(MQNotifyChannel.class);
        channel2.consume("I'm kisho!");
        System.out.println();
        System.out.println();

        DefaultHandlerDispatch handler2 = context.getBean(DefaultHandlerDispatch.class);
        Context context2 = new Context("023", "2000", "1111");
        handler2.dispatch(context2);
        System.out.println();
        System.out.println();

        handler2 = context.getBean(DefaultHandlerDispatch.class);
        context2 = new Context("023", "2001", "1111");
        handler2.dispatch(context2);
        System.out.println();
        System.out.println();

        handler2 = context.getBean(DefaultHandlerDispatch.class);
        context2 = new Context("023", "2002", "1111");
        handler2.dispatch(context2);
        System.out.println();
        System.out.println();

        handler2 = context.getBean(DefaultHandlerDispatch.class);
        context2 = new Context("023", "2003", "1111");
        handler2.dispatch(context2);
        System.out.println();
        System.out.println();

        handler2 = context.getBean(DefaultHandlerDispatch.class);
        context2 = new Context("023", "2004", "1111");
        handler2.dispatch(context2);
        System.out.println();
        System.out.println();

        handler2 = context.getBean(DefaultHandlerDispatch.class);
        context2 = new Context("023", "3000", "1111");
        handler2.dispatch(context2);
        System.out.println();
        System.out.println();

        handler2 = context.getBean(DefaultHandlerDispatch.class);
        context2 = new Context("023", "3001", "1111");
        handler2.dispatch(context2);
        System.out.println();
        System.out.println();

        handler2 = context.getBean(DefaultHandlerDispatch.class);
        context2 = new Context("023", "3002", "1111");
        handler2.dispatch(context2);
        System.out.println();
        System.out.println();

        handler2 = context.getBean(DefaultHandlerDispatch.class);
        context2 = new Context("023", "3003", "1111");
        handler2.dispatch(context2);
        System.out.println();
        System.out.println();

        handler2 = context.getBean(DefaultHandlerDispatch.class);
        context2 = new Context("023", "3004", "1111");
        handler2.dispatch(context2);
        System.out.println();
        System.out.println();

    }
}

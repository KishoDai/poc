package cn.xiangme.fintech.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("handlerDispatch")
public class DispatchResponsibilityHandler extends ResponsibilityHandler {

    private static final Logger LOG = LoggerFactory.getLogger(DispatchResponsibilityHandler.class);

    @Override
    protected Context executeNext(Context context) {
        if (getNext() == null) {
            ResponsibilityHandler handler = getHandler(HANDLER_1, context.getChannel());
            if (context.getLevels() == null || context.getLevels().isEmpty()) {
                context.setLevels(handler.getLevels());
            }
            return handler.handle(context);
        } else {
            context.setLevels(getNext().getLevels());
            context = getNext().handle(context);
        }
        return context;
    }


    @Override
    public Context echo(Context context) {
        LOG.info("echo()......");
        return context;
    }

}

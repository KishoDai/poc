package cn.xiangme.fintech.core;

import org.springframework.stereotype.Component;

@Component("handlerDispatch")
public class DispatchResponsibilityHandler extends ResponsibilityHandler {

    @Override
    public Context handle(Context context) {
        if (context == null) {
            return null;
        }
        Context newContext = echo(context);
        ResponsibilityHandler handler = getHandler(HANDLER_1, context.getChannel());
        return handler.handle(newContext);
    }

    @Override
    public Context echo(Context context) {
        return context;
    }

}

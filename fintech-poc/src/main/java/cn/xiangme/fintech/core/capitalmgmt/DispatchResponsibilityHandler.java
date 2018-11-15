package cn.xiangme.fintech.core.capitalmgmt;

import org.springframework.stereotype.Component;

@Component("handlerDispatch")
public class DispatchResponsibilityHandler extends ResponsibilityHandler {

    @Override
    public Context handle(Context context) {
        if (context == null) {
            return null;
        }
        Context newContext = echo(context);
        ResponsibilityHandler handler = getHandler(HANDLER_1, context.getCapitalNo());
        return handler.handle(newContext);
    }

    @Override
    public Context echo(Context context) {
        return context;
    }

}

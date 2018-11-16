package cn.xiangme.fintech.core.capitalmgmt;

import javax.annotation.Resource;

public abstract class AbstractNotifyChannel implements IChannel {

    @Resource
    private IHandlerDispatch handlerDispatch;

    public Context process(Context context) {
        context.setState(getState(context));
        return handlerDispatch.dispatch(context);
    }

    public abstract String getState(Context context);
}

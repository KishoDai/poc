package cn.xiangme.fintech.core.capitalmgmt;

import javax.annotation.Resource;

public abstract class AbstractApplyChannel implements IChannel {

    @Resource
    private IHandlerDispatch handlerDispatch;

    public Context process(Context context) {
        context.setState(Constants.STATE_SAVE);
        return handlerDispatch.dispatch(context);
    }

}

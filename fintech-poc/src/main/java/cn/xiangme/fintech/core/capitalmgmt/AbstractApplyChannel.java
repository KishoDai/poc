package cn.xiangme.fintech.core.capitalmgmt;


public abstract class AbstractApplyChannel implements IChannel {

    private IHandlerDispatch handlerDispatch;

    public Context process(Context context) {
        context.setState(Constants.STATE_SAVE);
        return handlerDispatch.dispatch(context);
    }

}

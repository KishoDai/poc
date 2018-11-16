package cn.xiangme.fintech.core.capitalmgmt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Component(Constants.HANDLER_DISPATCH)
public class DefaultHandlerDispatch implements IHandlerDispatch {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultHandlerDispatch.class);

    @Resource
    private IResponsibilityHandlerMap responsibilityHandlerMap;

    public Context dispatch(Context context) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("{}", context.toString());
        }

        if (context == null) {
            LOG.warn("Context is null!");
            return context;
        }
        if (context.done()) {
            LOG.debug("Context is done!");
            return context;
        }
        if (context.getType() == null || "".equals(context.getType())) {
            LOG.error("capitalNo is blank!");
            return context;
        }

        List<ResponsibilityHandler> handlerList = null;
        if (isSaveHandler(context.getState())) {
            handlerList = Arrays.asList(responsibilityHandlerMap.getHandlerByName(Constants.HANDLER_SAVE));
        } else {
            handlerList = responsibilityHandlerMap.getHandlersByState(context.getType(), context.getState());
        }
        if (handlerList == null || handlerList.isEmpty()) {
            LOG.warn("No found suitable ResponsibilityHandler!");
            return context;
        }
        for (ResponsibilityHandler handler : handlerList) {
            context = handler.handle(context);
            if (context.done()) {
                break;
            }
            context = dispatch(context);
        }
        return context;
    }

    private boolean isSaveHandler(String state) {
        return Constants.STATE_SAVE.equals(state);
    }

}

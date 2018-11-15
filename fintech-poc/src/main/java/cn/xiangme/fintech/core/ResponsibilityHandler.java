package cn.xiangme.fintech.core;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @author kisho
 */
public abstract class ResponsibilityHandler implements Serializable {

    public static final String HANDLER_SAVE = "Save";
    public static final String HANDLER_DISPATCH = "Dispatch";
    public static final String HANDLER_1 = "1";
    public static final String HANDLER_2 = "2";
    public static final String HANDLER_3 = "3";
    public static final String HANDLER_4 = "4";
    public static final String HANDLER_5 = "5";

    @Resource
    private IResponsibilityHandlerMap handlerMap;

    public abstract Context echo(Context context);

    private List<String> levels;

    private ResponsibilityHandler next;

    public final Context handle(Context context) {
        if (context == null) {
            return null;
        }

        Context newContext = context;
        if (canHandle(context.getLevels())) {
            newContext = echo(context);
        }

        if (newContext == null) {
            return null;
        }

        newContext = executeNext(newContext);
        return newContext;
    }

    public void setNext(ResponsibilityHandler handler) {
        next = handler;
    }

    public void setLevels(List<String> levels) {
        this.levels = levels;
    }

    protected ResponsibilityHandler getHandler(String step, String channel) {
        return handlerMap.getHandler(step, channel);
    }

    protected Context executeNext(Context newContext) {
        if (getNext() != null) {
            newContext = getNext().handle(newContext);
        }
        return newContext;
    }

    protected List<String> getLevels() {
        return levels;
    }

    protected ResponsibilityHandler getNext() {
        return next;
    }

    private boolean canHandle(List<String> currentLevels) {
        return ((currentLevels == null || currentLevels.isEmpty())
                && (getLevels() == null || getLevels().isEmpty())
        ) || (currentLevels != null
                && getLevels() != null
                && getLevels().containsAll(currentLevels));
    }

}

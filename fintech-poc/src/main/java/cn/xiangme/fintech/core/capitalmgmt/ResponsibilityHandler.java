package cn.xiangme.fintech.core.capitalmgmt;

import java.io.Serializable;
import java.util.List;

/**
 * @author kisho
 */
public abstract class ResponsibilityHandler implements Serializable {

    public abstract Context process(Context context);

    public abstract List<String> getStates();

    public final Context handle(Context context) {
        if (context == null) {
            return null;
        }
        if (context.done()) {
            return context;
        }

        if (canHandle(context.getState())) {
            context = process(context);
        }
        return context;
    }

    private boolean canHandle(String state) {
        return ((state == null || "".equals(state))
                && (getStates() == null || getStates().isEmpty())
        ) || (state != null
                && getStates() != null
                && getStates().contains(state));
    }

}

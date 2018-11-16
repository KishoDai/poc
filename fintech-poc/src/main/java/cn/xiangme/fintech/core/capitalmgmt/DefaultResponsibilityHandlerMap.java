package cn.xiangme.fintech.core.capitalmgmt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.*;

@Component
public class DefaultResponsibilityHandlerMap implements IResponsibilityHandlerMap {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultResponsibilityHandlerMap.class);

    private static final Map<String, ResponsibilityHandler> HANDLER_MAP = new HashMap<String, ResponsibilityHandler>();

    @Resource
    private ApplicationContext applicationContext;

    public ResponsibilityHandler getHandlerByName(String type, String step) {
        String handlerName = MessageFormat.format(Constants.HANDLER_STEP, type, step);
        return getHandlerByName(handlerName);
    }

    public ResponsibilityHandler getHandlerByName(String handlerName) {
        if (HANDLER_MAP.containsKey(handlerName)) {
            return HANDLER_MAP.get(handlerName);
        }
        return null;
    }

    public List<ResponsibilityHandler> getHandlersByState(String type, String state) {
        if (type == null || state == null || HANDLER_MAP.isEmpty()) {
            return Collections.emptyList();
        }
        List<ResponsibilityHandler> resultList = new ArrayList<ResponsibilityHandler>();
        for (Map.Entry<String, ResponsibilityHandler> entry : HANDLER_MAP.entrySet()) {
            boolean containsState = entry.getKey().startsWith(Constants.HANDLER + type)
                    && entry.getValue().getStates().contains(state);
            if (containsState) {
                resultList.add(entry.getValue());
            }
        }
        return resultList;
    }

    @PostConstruct
    private void init() {
        String[] handlerNames = applicationContext.getBeanNamesForType(ResponsibilityHandler.class);
        if (handlerNames == null || handlerNames.length == 0) {
            LOG.warn("No found any ResponsibilityHandler!");
        }

        for (String handlerName : handlerNames) {
            HANDLER_MAP.put(handlerName, applicationContext.getBean(handlerName, ResponsibilityHandler.class));
        }
    }

}

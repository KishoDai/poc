package cn.xiangme.fintech.core;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class DefaultResponsibilityHandlerMap implements IResponsibilityHandlerMap {

    @Resource
    private ApplicationContext applicationContext;

    private Map<String, String> handlerNameMap;

    public ResponsibilityHandler getHandler(String step, String channel) {
        String handlerName = step;
        if (channel != null && !"".equals(channel.trim())) {
            handlerName = channel + "_" + handlerName;
        }
        if (handlerNameMap != null) {
            handlerName = handlerNameMap.get(handlerName);
        }
        if (handlerName != null) {
            handlerName = "handler" + handlerName;
        }
        return applicationContext.getBean(handlerName, ResponsibilityHandler.class);
    }

    public void setHandlerNameMap(Map<String, String> handlerNameMap) {
        this.handlerNameMap = handlerNameMap;
    }

}

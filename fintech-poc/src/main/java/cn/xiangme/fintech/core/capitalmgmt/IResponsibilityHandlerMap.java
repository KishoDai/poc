package cn.xiangme.fintech.core.capitalmgmt;

import java.util.List;

public interface IResponsibilityHandlerMap {

    ResponsibilityHandler getHandlerByName(String type, String step);

    ResponsibilityHandler getHandlerByName(String handlerName);

    List<ResponsibilityHandler> getHandlersByState(String type, String state);

}

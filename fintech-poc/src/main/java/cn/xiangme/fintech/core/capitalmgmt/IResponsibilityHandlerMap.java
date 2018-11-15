package cn.xiangme.fintech.core.capitalmgmt;

public interface IResponsibilityHandlerMap {

    ResponsibilityHandler getHandler(String step, String channel);

}

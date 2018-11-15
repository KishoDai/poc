package cn.xiangme.fintech.core;

public interface IResponsibilityHandlerMap {

    ResponsibilityHandler getHandler(String step, String channel);

}

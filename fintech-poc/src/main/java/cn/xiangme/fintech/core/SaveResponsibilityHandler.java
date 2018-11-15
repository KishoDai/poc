package cn.xiangme.fintech.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component("handlerSave")
public class SaveResponsibilityHandler extends ResponsibilityHandler {

    private static final Logger LOG = LoggerFactory.getLogger(SaveResponsibilityHandler.class);

    @Resource
    private IService service;

    @Override
    public Context echo(Context context) {
        LOG.info("echo()......");
        return service.save(context);
    }

    @PostConstruct
    private void init() {
        setNext(getHandler(HANDLER_DISPATCH, null));
    }

}

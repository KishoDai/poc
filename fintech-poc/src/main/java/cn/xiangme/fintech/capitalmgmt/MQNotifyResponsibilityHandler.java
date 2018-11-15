package cn.xiangme.fintech.capitalmgmt;

import cn.xiangme.fintech.core.Context;
import cn.xiangme.fintech.core.ResponsibilityHandler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MQNotifyResponsibilityHandler extends ResponsibilityHandler {


    @Override
    public Context echo(Context context) {
        //TODO
        return context;
    }

    @PostConstruct
    private void init() {
        setNext(getHandler(HANDLER_SAVE, null));
    }

}

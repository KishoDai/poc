package cn.xiangme.fintech.capitalmgmt;

import cn.xiangme.fintech.core.Context;
import cn.xiangme.fintech.core.ResponsibilityHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MQChannelResponsibilityHandler extends ResponsibilityHandler {

    private static final Logger LOG = LoggerFactory.getLogger(MQChannelResponsibilityHandler.class);

    public void consume(String s) {
        // TODO
        LOG.debug("consume()......");
        Context context = new Context("023", "123456789", null, s);
        handle(context);
    }

    @Override
    public Context echo(Context context) {
        return context;
    }

    @PostConstruct
    private void init() {
        setNext(getHandler(HANDLER_SAVE, null));
    }

}

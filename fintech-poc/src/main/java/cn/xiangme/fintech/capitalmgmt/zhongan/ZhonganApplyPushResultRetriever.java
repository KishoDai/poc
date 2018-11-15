package cn.xiangme.fintech.capitalmgmt.zhongan;

import cn.xiangme.fintech.core.capitalmgmt.Context;
import cn.xiangme.fintech.core.capitalmgmt.ResponsibilityHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ZhonganApplyPushResultRetriever extends ResponsibilityHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ZhonganApplyPushResultRetriever.class);

    public void consume(String s) {
        LOG.debug("consume()......");
        Context context = new Context("023", "123456789", "4000", s);
        handle(context);
    }

    @Override
    public Context echo(Context context) {
        return context;
    }

    @PostConstruct
    private void init() {
        setNext(getHandler(HANDLER_DISPATCH, null));
    }

}

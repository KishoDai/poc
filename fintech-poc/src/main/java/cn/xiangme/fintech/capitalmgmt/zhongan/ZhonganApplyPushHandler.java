package cn.xiangme.fintech.capitalmgmt.zhongan;

import cn.xiangme.fintech.core.capitalmgmt.Context;
import cn.xiangme.fintech.core.capitalmgmt.ResponsibilityHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service("handler023_1")
public class ZhonganApplyPushHandler extends ResponsibilityHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ZhonganApplyPushHandler.class);

    @Override
    public Context echo(Context context) {
        //TODO
        LOG.info("echo()......");
        context.setState("4000");
        return context;
    }

    @PostConstruct
    private void init() {
        setStates(Arrays.asList("3000"));
        setNext(getHandler(HANDLER_2, "023"));
    }

}

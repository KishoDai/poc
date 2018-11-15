package cn.xiangme.fintech.capitalmgmt.zhongan;

import cn.xiangme.fintech.core.capitalmgmt.Context;
import cn.xiangme.fintech.core.capitalmgmt.ResponsibilityHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service("handler023_2")
public class ZhonganApplyPushResultHandler extends ResponsibilityHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ZhonganApplyPushResultHandler.class);

    @Override
    public Context echo(Context context) {
        //TODO
        LOG.info("echo()......");
        context.setState("5000");
        return context;
    }

    @PostConstruct
    private void init() {
        setStates(Arrays.asList("4000"));
        setNext(getHandler("3", "023"));
    }

}

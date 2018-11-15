package cn.xiangme.fintech.capitalmgmt;

import cn.xiangme.fintech.core.Context;
import cn.xiangme.fintech.core.ResponsibilityHandler;
import cn.xiangme.fintech.core.SaveResponsibilityHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service("handler023_1")
public class XiaoyudianApplyPushHandler extends ResponsibilityHandler {

    private static final Logger LOG = LoggerFactory.getLogger(XiaoyudianApplyPushHandler.class);

    @Override
    public Context echo(Context context) {
        //TODO
        LOG.info("echo()......");
        return context;
    }

    @PostConstruct
    private void init() {
        setLevels(Arrays.asList("3000", "3001"));
        setNext(getHandler(HANDLER_2, "023"));
    }

}

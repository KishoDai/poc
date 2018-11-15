package cn.xiangme.fintech.capitalmgmt;

import cn.xiangme.fintech.core.Context;
import cn.xiangme.fintech.core.ResponsibilityHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service("handler023_2")
public class XiaoyudianApproveHandler extends ResponsibilityHandler {

    private static final Logger LOG = LoggerFactory.getLogger(XiaoyudianApproveHandler.class);

    @Override
    public Context echo(Context context) {
        //TODO
        LOG.info("echo()......");
        return context;
    }

    @PostConstruct
    private void init() {
        setLevels(Arrays.asList("4000", "4001"));
        setNext(getHandler("3", "023"));
    }

}

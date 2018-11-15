package cn.xiangme.fintech.capitalmgmt;

import cn.xiangme.fintech.core.Context;
import cn.xiangme.fintech.core.ResponsibilityHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service("handler023_3")
public class XiaoyudianLoanApplyHandler extends ResponsibilityHandler {

    private static final Logger LOG = LoggerFactory.getLogger(XiaoyudianLoanApplyHandler.class);

    @Override
    public Context echo(Context context) {
        //TODO
        LOG.info("echo()......");
        return context;
    }

    @PostConstruct
    private void init() {
        setLevels(Arrays.asList("5000", "5001"));
    }

}

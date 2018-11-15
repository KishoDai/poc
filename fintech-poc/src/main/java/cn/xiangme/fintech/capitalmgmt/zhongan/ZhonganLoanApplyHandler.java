package cn.xiangme.fintech.capitalmgmt.zhongan;

import cn.xiangme.fintech.core.capitalmgmt.Context;
import cn.xiangme.fintech.core.capitalmgmt.ResponsibilityHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service("handler023_3")
public class ZhonganLoanApplyHandler extends ResponsibilityHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ZhonganLoanApplyHandler.class);

    @Override
    public Context echo(Context context) {
        //TODO
        LOG.info("echo()......");
        return context;
    }

    @PostConstruct
    private void init() {
        setStates(Arrays.asList("5000", "5001"));
    }

}

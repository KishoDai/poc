package cn.xiangme.fintech.core.capitalmgmt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Component(Constants.HANDLER_SAVE)
public class SaveResponsibilityHandler extends ResponsibilityHandler {

    private static final Logger LOG = LoggerFactory.getLogger(SaveResponsibilityHandler.class);

    @Resource
    private IService service;

    @Override
    public Context process(Context context) {
        LOG.info("process()......");
        return service.save(context);
    }

    @Override
    public List<String> getStates() {
        return Arrays.asList(Constants.STATE_SAVE);
    }


}

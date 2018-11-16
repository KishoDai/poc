package cn.xiangme.fintech.capitalmgmt;

import cn.xiangme.fintech.core.capitalmgmt.AbstractApplyChannel;
import cn.xiangme.fintech.core.capitalmgmt.Constants;
import cn.xiangme.fintech.core.capitalmgmt.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MQApplyApplyChannel extends AbstractApplyChannel {

    private static final Logger LOG = LoggerFactory.getLogger(MQApplyApplyChannel.class);

    public void consume(String s) {
        // TODO
        LOG.debug("consume()......");
        Context context = new Context("023", s);
        process(context);
    }

}

package cn.xiangme.fintech.capitalmgmt;

import cn.xiangme.fintech.core.capitalmgmt.AbstractNotifyChannel;
import cn.xiangme.fintech.core.capitalmgmt.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MQNotifyChannel extends AbstractNotifyChannel {

    private static final Logger LOG = LoggerFactory.getLogger(MQNotifyChannel.class);

    public void consume(String content) {
        LOG.debug("consume()......");
        Context context = new Context("023", content);
        process(context);
    }

    @Override
    public String getState(Context context) {
        return "2001";
    }
}

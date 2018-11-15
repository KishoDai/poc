package cn.xiangme.fintech.capitalmgmt;

import cn.xiangme.fintech.core.Context;
import cn.xiangme.fintech.core.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class IServiceImpl implements IService {

    private static final Logger LOG = LoggerFactory.getLogger(IServiceImpl.class);

    public Context save(Context context) {
        // TODO
        LOG.info("validate successfully!");
        LOG.info("save successfully!");
        return context;
    }

}

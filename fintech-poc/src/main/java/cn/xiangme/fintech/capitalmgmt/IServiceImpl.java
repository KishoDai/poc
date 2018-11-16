package cn.xiangme.fintech.capitalmgmt;

import cn.xiangme.fintech.core.capitalmgmt.Context;
import cn.xiangme.fintech.core.capitalmgmt.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IServiceImpl implements IService {

    private static final Logger LOG = LoggerFactory.getLogger(IServiceImpl.class);

    public Context save(Context context) {
        // TODO
        LOG.info("validate successfully!");
        LOG.info("save successfully!");
        context.setState("2000");
        return context;
    }

    public List<Context> retrieveRecords4Compensation() {
        //TODO
        List<Context> contexts = new ArrayList<Context>();
        contexts.add(new Context("023", "2000", "I'm ok"));
        contexts.add(new Context("023", "2001", "I'm ok"));
        return contexts;
    }

}

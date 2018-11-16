package cn.xiangme.fintech.core.capitalmgmt;

import javax.annotation.Resource;
import java.util.List;

public class BaseJob {

    @Resource
    private IService service;

    @Resource
    private IHandlerDispatch dispatch;

    public void execute() {
        // 补偿策略 //TODO
        List<Context> contexts = service.retrieveRecords4Compensation();
        if (contexts == null || contexts.isEmpty()) {
            return;
        }
        for (Context context : contexts) {
            dispatch.dispatch(context);
        }
    }
}

package cn.xiangme.fintech.capitalmgmt.third;

import cn.xiangme.fintech.core.capitalmgmt.Context;
import cn.xiangme.fintech.core.capitalmgmt.ResponsibilityHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(ThirdConstant.HANDLER_APPLY_PUSH_RESULT_RETRIEVE)
public class ThirdApplyPushResultRetrieveHandler extends ResponsibilityHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ThirdApplyPushResultRetrieveHandler.class);

    @Override
    public Context process(Context context) {
        LOG.info("process()......");
        //TODO 调用第三方查询授信申请审核结果
        boolean applyResult = true;// 授信申请审核结果
        if (applyResult) {
            // TODO 更新授信申请审核状态为成功
            context.setState("2004");// 授信申请成功
            // TODO
            context.setState("3000");// 放款初始状态
            return context;
        } else {
            // TODO 更新授信申请审核状态为拒件或者失败
            context.setState("2002");// 授信申请异常
            // TODO
            context.setState("2003");// 授信申请拒件
            context.finish();
            return context;
        }
    }

    @Override
    public List<String> getStates() {
        return ThirdEnum.APPLY_PUSH_RESULT_RETRIEVE.getStates();
    }

}

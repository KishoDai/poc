package cn.xiangme.fintech.capitalmgmt.third;

import cn.xiangme.fintech.core.capitalmgmt.Context;
import cn.xiangme.fintech.core.capitalmgmt.ResponsibilityHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(ThirdConstant.HANDLER_LOAN_APPLY_RESULT_RETRIEVE)
public class ThirdLoanApplyResultRetrieveHandler extends ResponsibilityHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ThirdLoanApplyResultRetrieveHandler.class);

    @Override
    public Context process(Context context) {
        LOG.info("process()...");
        boolean loanResult = true;// 放款申请审核结果
        if (loanResult) {
            // TODO 更新放款申请审核状态为成功
            context.setState("3004");// 放款申请成功
            context.finish();
            return context;
        } else {
            // TODO 更新放款申请审核状态为拒件或者失败
            context.setState("3002");// 放款申请异常
            // TODO
            context.setState("3003");// 放款申请拒件
            context.finish();
            return context;
        }
    }

    @Override
    public List<String> getStates() {
        return ThirdEnum.LOAN_APPLY_RESULT_RETRIEVE.getStates();
    }

}

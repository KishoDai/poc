package cn.xiangme.fintech.capitalmgmt.third;

import cn.xiangme.fintech.core.capitalmgmt.Context;
import cn.xiangme.fintech.core.capitalmgmt.ResponsibilityHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(ThirdConstant.HANDLER_LOAN_APPLY)
public class ThirdLoanApplyHandler extends ResponsibilityHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ThirdLoanApplyHandler.class);

    @Override
    public Context process(Context context) {
        LOG.info("process()......");
        //TODO 调用第三方放款申请审核
        //TODO 处理第三方放款申请审核结果
        //TODO 第三方是否同步返回
        boolean thirdResult = true;
        if (thirdResult) {// 同步返回
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
        } else { //异步返回
            //TODO 更新当前状态为申请审核中。待后续异步通知或者异步查询，此笔职责链结束
            context.setState("3001");// 放款申请审核中
            context.finish();
            return context;
        }
    }

    @Override
    public List<String> getStates() {
        return ThirdEnum.LOAN_APPLY.getStates();
    }

}

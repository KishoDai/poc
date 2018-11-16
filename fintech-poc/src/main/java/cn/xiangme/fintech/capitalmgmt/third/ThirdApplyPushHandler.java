package cn.xiangme.fintech.capitalmgmt.third;

import cn.xiangme.fintech.core.capitalmgmt.Context;
import cn.xiangme.fintech.core.capitalmgmt.ResponsibilityHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(ThirdConstant.HANDLER_APPLY_PUSH)
public class ThirdApplyPushHandler extends ResponsibilityHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ThirdApplyPushHandler.class);

    @Override
    public Context process(Context context) {
        LOG.info("process()......");

        //TODO 调用第三方授信申请审核
        //TODO 处理第三方授信申请审核结果
        //TODO 第三方是否同步返回
        boolean thirdResult = true;
        if (thirdResult) {// 同步返回
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
        } else { //异步返回
            //TODO 更新当前状态为申请审核中。待后续异步通知或者异步查询，此笔职责链结束
            context.setState("2001");// 授信申请审核中
            context.finish();
            return context;
        }
    }

    @Override
    public List<String> getStates() {
        return ThirdEnum.APPLY_PUSH.getStates();
    }

}

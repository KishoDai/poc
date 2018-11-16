package cn.xiangme.fintech.capitalmgmt.third;

import java.util.Arrays;
import java.util.List;

enum ThirdEnum {
    // 1000-初始状态
    // 2000-授信申请初始状态，2001-授信申请审核中，2002-授信申请异常，2003-授信申请拒件，2004-授信申请审核成功,
    // 3000-放款申请初始状态，3001-放款申请审核中，3002-放款申请异常，3003-放款申请拒件，3004-放款申请成功
    APPLY_PUSH(ThirdConstant.HANDLER_APPLY_PUSH, Arrays.asList("2000", "2002")),
    APPLY_PUSH_RESULT_RETRIEVE(ThirdConstant.HANDLER_APPLY_PUSH_RESULT_RETRIEVE, Arrays.asList("2001")),
    LOAN_APPLY_RESULT_RETRIEVE(ThirdConstant.HANDLER_LOAN_APPLY_RESULT_RETRIEVE, Arrays.asList("3001")),
    LOAN_APPLY(ThirdConstant.HANDLER_LOAN_APPLY, Arrays.asList("3000", "3002"));

    private String handler;
    private List<String> handleStates;

    ThirdEnum(String handler, List<String> handleStates) {
        this.handler = handler;
        this.handleStates = handleStates;
    }

    public String getHandler() {
        return handler;
    }

    public List<String> getStates() {
        return handleStates;
    }

}

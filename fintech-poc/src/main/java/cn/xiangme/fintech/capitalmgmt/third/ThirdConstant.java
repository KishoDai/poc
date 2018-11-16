package cn.xiangme.fintech.capitalmgmt.third;

import static cn.xiangme.fintech.core.capitalmgmt.Constants.*;

final class ThirdConstant {
    private ThirdConstant() {

    }

    private static final String CAPITAL_NO = "023";
    private static final String PREFIX = HANDLER + CAPITAL_NO;
    public static final String HANDLER_APPLY_PUSH = PREFIX + "_1";
    public static final String HANDLER_APPLY_PUSH_RESULT_RETRIEVE = PREFIX + "_2";
    public static final String HANDLER_LOAN_APPLY = PREFIX + "_3";
    public static final String HANDLER_LOAN_APPLY_RESULT_RETRIEVE = PREFIX + "_4";

}

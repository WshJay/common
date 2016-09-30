package org.wsh.common.test.eum;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 提现审核状态
 * @since Date： 2016-08-19 17:42
 */
public enum WithdrawStatus {

    // 待审核
    WAIT_AUDIT,

    // 审核成功
    AUDIT_SUCCESS,

    // 审核失败
    AUDIT_FAIL,

    // 核实失败
    VERIFY_FAIL,

    // 待打款
    WAIT_PAYMENT,

    // 处理中
    DOING,

    // 处理完成
    SUCCESS,

    // 处理失败
    FAILURE,

    // 已撤销
    CANCEL,

    // 已关闭
    CLOSE,

    // 网关失败
    BADGATEWAY

}

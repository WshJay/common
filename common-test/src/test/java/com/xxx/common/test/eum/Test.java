package com.xxx.common.test.eum;

import static com.xxx.common.test.eum.ObjToEnum.objToEnum;
import static com.xxx.common.test.eum.WithdrawStatus.AUDIT_SUCCESS;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 对象OBJ转枚举
 * @since Date： 2016-08-25 15:48
 */
public class Test {

    @org.junit.Test
    public void test(){
        Object obj = "AUDIT_SUCCESS";
        WithdrawStatus withdrawStatus = ObjToEnum.objToEnum(obj);
        System.out.println(withdrawStatus);
        System.out.println(withdrawStatus.equals(AUDIT_SUCCESS));
        System.out.println(withdrawStatus == AUDIT_SUCCESS);
        System.out.println();
    }
}

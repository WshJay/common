package org.wsh.common.test.eum;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 枚举转换
 * @since Date： 2016-08-25 15:44
 */
public class EnumUtil {

    public static WithdrawStatus objToEnum(Object obj) {
        return WithdrawStatus.valueOf(String.valueOf(obj));
    }

    public static String EnumToString(WithdrawStatus status){
        return String.valueOf(status);
    }
}

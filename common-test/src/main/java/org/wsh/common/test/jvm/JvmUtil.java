package org.wsh.common.test.jvm;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  JVM工具类
 * since Date： 2017-02-03 14:10
 */
public class JvmUtil {

    /**
     * 获取JVM堆大小
     * @return long
     */
    public static long getXmxValue(){
        return Runtime.getRuntime().maxMemory()/1000/1000;
    }
}

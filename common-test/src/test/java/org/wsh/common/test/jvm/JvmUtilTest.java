package org.wsh.common.test.jvm;

import org.junit.Test;
import org.wsh.common.util.logger.LoggerService;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-02-03 14:15
 */
public class JvmUtilTest extends LoggerService {

    @Test
    public void getXmxTest(){
        logger.info("-Xmx" + JvmUtil.getXmxValue() + "M");
    }
}

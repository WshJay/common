package org.wsh.common.support.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-09-29 15:19
 */
public abstract class AbstractLogger {

    public Logger logger = LoggerFactory.getLogger(this.getClass());
}

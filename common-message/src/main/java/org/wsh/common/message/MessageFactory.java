package org.wsh.common.message;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-11-04 11:00
 */
public interface MessageFactory {

    Object sendMessage(Object obj)throws Exception;
}
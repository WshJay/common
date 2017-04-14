package org.wsh.common.cache.message;

import java.io.Serializable;
import java.util.Map;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-04-11 17:13
 */
public interface MessageDelegate {

    public void handleMessage(String message);

    public void handleMessage(Map<?, ?> message);

    public void handleMessage(byte[] message);

    public void handleMessage(Serializable message);

    // pass the channel/pattern as well
    public void handleMessage(Serializable message, String channel);
}

package org.wsh.common.cache.message;

import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Map;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-04-11 17:15
 */
public class DefaultMessageDelegate implements MessageDelegate {

    @Override
    public void handleMessage(String message) {
        System.out.println("handleMessage(String message):" + message);
    }

    @Override
    public void handleMessage(Map<?, ?> message) {
        System.out.println("handleMessage(Map<?, ?> message):" + message);
    }

    @Override
    public void handleMessage(byte[] message) {
        System.out.println("handleMessage(byte[] message):"
                + new String(message));
    }

    @Override
    public void handleMessage(Serializable message) {
        System.out.println("handleMessage(Serializable message):"
                + message.toString());
    }

    @Override
    public void handleMessage(Serializable message, String channel) {
        System.out
                .println("handleMessage(Serializable message, String channel):"
                        + message.toString() + ", channel:" + channel);
    }
}

package org.wsh.common.cache.message.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import javax.annotation.Resource;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  消息监听器
 * since Date： 2017-04-11 16:47
 */
public class RedisMessageListener implements MessageListener {

    @Resource
    private JdkSerializationRedisSerializer jdkSerializationRedisSerializer;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        if (new String(message.getChannel()).equals("order")){
            System.out.println("channel:" + new String(message.getChannel())
                    + ",message:" + jdkSerializationRedisSerializer.deserialize(message.getBody()));
        }else{
            System.out.println("channel:" + new String(message.getChannel())
                    + ",message:" + new String(message.getBody()));
        }
    }
}

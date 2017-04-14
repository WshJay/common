package org.wsh.common.cache.queue;

import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Service;
import org.wsh.common.cache.service.RedisService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-04-10 10:15
 */
@Service("taskQueueService")
public class TaskQueueService implements IQueue{

    @Resource
    private RedisService redisService;

    @Resource
    private JdkSerializationRedisSerializer jdkSerializer;

    @Override
    public long getTotalNum(String taskKey) {
        return redisService.llen(taskKey);
    }

    @Override
    public <T> List<T> getQueueList(String taskKey, Class<T> classz) {

        List<T> tList = null;
        tList = new ArrayList<>();
        if (classz.equals(String.class)) {
            List<String> list = redisService.lrange(taskKey);
            for (String s : list) {
                tList.add(classz.cast(s));
            }
        }else{
            List<byte[]> list = redisService.lrange(taskKey.getBytes());
            for (byte[] bytes : list) {
                tList.add(classz.cast(jdkSerializer.deserialize(bytes)));
            }
        }
        return tList;
    }

    @Override
    public <T> String addQueue(String taskKey, Class<T> classz, T t) {
        if (classz.equals(String.class)){
            redisService.rpush(taskKey,String.valueOf(t));
        }else{
            redisService.rpush(taskKey.getBytes(), jdkSerializer.serialize(t));
        }
        return taskKey;
    }


    @Override
    public <T> T popQueue(String taskKey, Class<T> classz) {
        if (classz.equals(String.class)){
            return classz.cast(redisService.lpop(taskKey));
        }else{
            return classz.cast(jdkSerializer.deserialize(redisService.lpop(taskKey.getBytes())));
        }
    }
}

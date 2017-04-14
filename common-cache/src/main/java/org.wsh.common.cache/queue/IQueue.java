package org.wsh.common.cache.queue;

import org.wsh.common.cache.queue.task.AbstractTask;

import java.io.IOException;
import java.util.List;

import static javafx.scene.input.KeyCode.T;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-04-10 10:28
 */
public interface IQueue {

    /**
     * 获取队列数量
     * @param taskKey key
     * @return 数量
     */
    public long getTotalNum(String taskKey);

    /**
     * 获取队列中的所有元素
     * @param taskKey key
     * @param <T> 元素对象
     * @return 列表
     */
    public <T>List<T> getQueueList(String taskKey, Class<T> classz);

    /**
     * 向队列中添加元素
     * @param taskKey key
     * @param classz 元素类型
     * @param t 元素
     * @return String
     */
    public <T>String addQueue(String taskKey, Class<T> classz, T t);

    /**
     * 消费队列中的元素
     * @param taskKey key
     * @param classz 元素类型
     * @return 元素信息
     */
    public <T>T popQueue(String taskKey, Class<T> classz);

}

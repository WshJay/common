package org.wsh.common.cache.queue.task;

import org.wsh.common.cache.queue.enums.TaskType;

import java.io.Serializable;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-04-10 10:29
 */
public abstract class AbstractTask implements Serializable{

    private TaskType key;

    public TaskType getKey() {
        return key;
    }

    public void setKey(TaskType key) {
        this.key = key;
    }

}

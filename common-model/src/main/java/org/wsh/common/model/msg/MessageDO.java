package org.wsh.common.model.msg;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.enums.msg.MessageType;
import org.wsh.common.model.base.BaseDO;

import java.util.Date;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-11-04 11:10
 */
public class MessageDO extends BaseDO{

    /**
     * 消息类型
     */
    @Setter
    @Getter
    private MessageType type;

    /**
     * 发送者ID
     */
    @Setter
    @Getter
    private String fromUserId;

    /**
     * 接收者ID
     */
    @Setter
    @Getter
    private String toUserId;

    /**
     * 发送的文本
     */
    @Setter
    @Getter
    private String TextMessage;

    /**
     * 发送者名称
     */
    @Setter
    @Getter
    private String fromUserName;
}

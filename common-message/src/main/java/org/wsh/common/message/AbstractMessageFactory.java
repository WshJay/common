package org.wsh.common.message;

import org.wsh.common.model.msg.MessageDO;

import static javafx.scene.input.KeyCode.M;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-11-04 11:06
 */
public abstract class AbstractMessageFactory implements MessageFactory{

    @Override
    public Object sendMessage(Object obj) throws Exception {

        MessageDO messageDO = (MessageDO) obj;
        return doSendMessage(messageDO);
    }

    abstract boolean doSendMessage(MessageDO messageDO)throws Exception;
}

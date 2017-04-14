package org.wsh.common.test.order.duoju;

import org.wsh.common.support.response.ResponseDO;
import org.wsh.common.test.order.duoju.base.IProcessor;
import org.wsh.common.test.order.duoju.base.OrderIProcessor;
import org.wsh.common.test.order.duoju.enums.PartyType;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-03-13 15:52
 */
public class Client {

    public static void main(String[] args) {
        IProcessor iProcessor = OrderIProcessor.getProcessor(PartyType.OFFICIAL_PARTY.KEY);
        iProcessor.addOrder();
        IProcessor iProcessor1 = OrderIProcessor.getProcessor(PartyType.USER_PARTY.KEY);
        iProcessor1.addOrder();
    }
}

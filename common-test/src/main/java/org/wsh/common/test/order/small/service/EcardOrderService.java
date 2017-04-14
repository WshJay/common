package org.wsh.common.test.order.small.service;

import org.wsh.common.test.order.small.enums.OrderType;
import org.wsh.common.test.order.small.model.BalanceOrderDO;
import org.wsh.common.test.order.small.model.OrderDO;
import org.wsh.common.test.order.small.factory.AbstractOrderFactory;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-03-09 19:51
 */
public class EcardOrderService extends AbstractOrderFactory {

    @Override
    public OrderDO addOrder() {
        System.out.println("添加E卡订单成功!");
        return new BalanceOrderDO();
    }

    @Override
    public OrderDO updateOrder(Long id) {
        System.out.println("修改ID=>[" + id + "]E卡订单成功!");
        return new BalanceOrderDO();
    }

    @Override
    public OrderType getOrderType() {
        return OrderType.ECARD;
    }
}

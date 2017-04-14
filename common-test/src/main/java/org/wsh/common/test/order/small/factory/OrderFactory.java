package org.wsh.common.test.order.small.factory;


import org.wsh.common.test.order.small.enums.OrderType;
import org.wsh.common.test.order.small.model.OrderDO;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-03-09 19:45
 */
public interface OrderFactory {

    public OrderDO addOrder();


    public OrderDO updateOrder(Long id);
}

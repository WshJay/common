package org.wsh.common.test.order.small;


import org.wsh.common.test.order.small.factory.OrderFactory;
import org.wsh.common.test.order.small.service.BalanceOrderService;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-03-09 19:29
 */
public class Client {

    public static void main(String[] args) {
        OrderFactory orderFactory = new BalanceOrderService();
        OrderDO orderDO = orderFactory.addOrder();
        System.out.println(orderDO.getId());
    }
}

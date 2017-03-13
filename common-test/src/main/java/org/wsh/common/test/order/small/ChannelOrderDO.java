package org.wsh.common.test.order.small;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.test.order.small.enums.OrderType;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-03-09 19:20
 */
public class ChannelOrderDO extends OrderDO {

    private OrderType type = OrderType.CHANNEL;

    @Getter
    @Setter
    private String name;
}
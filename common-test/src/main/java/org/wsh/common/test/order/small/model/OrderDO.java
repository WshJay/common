package org.wsh.common.test.order.small.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-03-09 19:10
 */
public abstract class OrderDO{

    @Getter
    @Setter
    protected Long id;

    @Getter
    @Setter
    protected String orderNumber;

}

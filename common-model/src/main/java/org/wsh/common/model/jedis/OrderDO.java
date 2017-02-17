package org.wsh.common.model.jedis;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-02-17 15:32
 */
public class OrderDO implements Serializable{

    /**
     * ID
     */
    @Setter
    @Getter
    private Long id;

    /**
     * 金额
     */
    @Setter
    @Getter
    private BigDecimal amount;

    public OrderDO() {
    }

    public OrderDO(Long id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }
}

package org.wsh.common.cache.queue.task;

import org.wsh.common.cache.queue.enums.TaskType;
import org.wsh.common.cache.queue.task.AbstractTask;

import java.math.BigDecimal;
import java.util.Date;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-04-10 10:32
 */
public class OrderTask extends AbstractTask {

    public OrderTask() {
        setKey(TaskType.ORDER);
    }

    public OrderTask(String orderId) {
        setKey(TaskType.ORDER);
        this.orderId = orderId;
    }

    private String orderId;

    private String orderNum;

    private BigDecimal amount;

    private String goodsId;

    private Date createDate;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "OrderTask{" +
                "orderId='" + orderId + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", amount=" + amount +
                ", goodsId='" + goodsId + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}

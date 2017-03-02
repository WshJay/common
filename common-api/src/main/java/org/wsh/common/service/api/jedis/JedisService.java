package org.wsh.common.service.api.jedis;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-02-17 10:48
 */

import org.apache.ibatis.transaction.Transaction;
import org.wsh.common.model.jedis.OrderDO;
import org.wsh.common.support.response.ResponseDO;

import java.util.List;

/**
 * Jedis服务接口
 */
public interface JedisService {

    /**
     * 普通SET
     * 不能保证线程安全
     * @param orderDO OrderDO
     * @return ResponseDO
     */
    public ResponseDO normalSet(OrderDO orderDO);

    /**
     * Jedis事务
     * 调用jedis.watch(…)方法来监控key,如果调用后key值发生变化,则整个事务会执行失败;
     * 事务中某个操作失败,并不会回滚其他操作;可以使用discard()方法来取消事务.
     * 注意:Spring Factory生成的Jedis是不同客户端,因此使用Jedis事务必须保证事务使用的是同一个Jedis
     * @param orderDO OrderDO
     * @return ResponseDO
     */
    public ResponseDO transaction(OrderDO orderDO);

    /**
     * 管道(用于批量执行Redis操作,效率极高)
     * @param orderDOList List<OrderDO>
     */
    public ResponseDO pipelined(List<OrderDO> orderDOList);

    /**
     * 管道结合事务(用于批量执行Redis操作,效率极高)
     * @param orderDOList List<OrderDO>
     * @return ResponseDO
     */
    public ResponseDO pipelineAndTransaction(List<OrderDO> orderDOList);
}
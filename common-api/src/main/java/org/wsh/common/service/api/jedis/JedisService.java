package org.wsh.common.service.api.jedis;

import org.wsh.common.model.jedis.OrderDO;
import org.wsh.common.support.response.ResponseDO;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  Jedis基本操作
 * since Date： 2017/2/17 21:28
 */
public interface JedisService {

    public ResponseDO normalSet(OrderDO orderDO);

    public ResponseDO transaction(OrderDO orderDO);
}

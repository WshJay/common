package org.wsh.common.service.impl.jedis;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.wsh.common.cache.service.RedisService;
import org.wsh.common.enums.expection.Errors;
import org.wsh.common.model.jedis.OrderDO;
import org.wsh.common.service.api.jedis.JedisService;
import org.wsh.common.support.response.ResponseDO;
import org.wsh.common.util.logger.LoggerService;
import redis.clients.jedis.Transaction;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-02-17 10:51
 */
@Service("jedisService")
public class JedisServiceImpl extends LoggerService implements JedisService {

    @Resource
    private RedisService redisService;

    @Override
    public ResponseDO normalSet(OrderDO orderDO) {
        try {
            String currentAmountStr = redisService.get(String.valueOf(orderDO.getId()));
            BigDecimal currentAmount = new BigDecimal(0.0);
            if (StringUtils.isNotBlank(currentAmountStr)){
                currentAmount = new BigDecimal(currentAmountStr);
            }
            String result = redisService.set(String.valueOf(orderDO.getId()), String.valueOf(String.valueOf(currentAmount.add(orderDO.getAmount()))));
            if (result.equals("OK")){
                logger.info("ID==>[" + orderDO.getId() + "] Amount:[" + orderDO.getAmount() + "]");
                return new ResponseDO();
            }else{
                return new ResponseDO(Errors.DEFAULT_ERROR);
            }
        } catch (Exception e) {
            logger.error("Redis SET Exception!",e);
            return new ResponseDO(Errors.DEFAULT_ERROR);
        }
    }

    @Override
    public ResponseDO transaction(OrderDO orderDO) {

        try {
            Transaction tx = redisService.multi();
            String currentAmountStr = redisService.get(String.valueOf(orderDO.getId()));
            BigDecimal currentAmount = new BigDecimal(0.0);
            if (StringUtils.isNotBlank(currentAmountStr)){
                currentAmount = new BigDecimal(currentAmountStr);
            }
            tx.set(String.valueOf(orderDO.getId()), String.valueOf(currentAmount.add(orderDO.getAmount())));
            tx.exec();
            redisService.disconnect();
            logger.info("ID==>[" + orderDO.getId() + "] Amount:[" + orderDO.getAmount() + "]");
            return new ResponseDO();
        } catch (Exception e) {
            logger.error("Redis Transaction SET Exception!",e);
            return new ResponseDO(Errors.DEFAULT_ERROR);
        }
    }
}

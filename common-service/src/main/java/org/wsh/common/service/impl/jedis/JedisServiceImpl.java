package org.wsh.common.service.impl.jedis;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.wsh.common.cache.service.RedisService;
import org.wsh.common.enums.expection.Errors;
import org.wsh.common.model.jedis.OrderDO;
import org.wsh.common.service.api.jedis.JedisService;
import org.wsh.common.support.response.ResponseDO;
import org.wsh.common.util.collections.CollectionUtils;
import org.wsh.common.util.logger.LoggerService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
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
            logger.debug("当前金额=>["+ currentAmount + "]");
            String result = redisService.set(String.valueOf(orderDO.getId()), String.valueOf(String.valueOf(currentAmount.add(orderDO.getAmount()))));
            if (result.equals("OK")){
                logger.info("ID=>[" + orderDO.getId() + "]增加Amount:[" + orderDO.getAmount() + "]成功!");
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
            Assert.notNull(orderDO,Errors.PARAMETER_IS_ERROR.getErrorMsg());
            Assert.notNull(orderDO.getId(),Errors.PARAMETER_IS_ERROR.getErrorMsg());
            Assert.notNull(orderDO.getAmount(),Errors.PARAMETER_IS_ERROR.getErrorMsg());

            Jedis jedis = redisService.getJedis();
            String key = String.valueOf(orderDO.getId());
            jedis.watch(key);
            Transaction tx = jedis.multi();
            logger.debug("===进入Redis事务===");
            String currentAmountStr = redisService.get(String.valueOf(orderDO.getId()));
            BigDecimal currentAmount = new BigDecimal(0.0);
            if (StringUtils.isNotBlank(currentAmountStr)){
                currentAmount = new BigDecimal(currentAmountStr);
            }
            logger.debug("当前金额=>[" + currentAmount + "]");
            Thread.sleep(6000);
            tx.set(String.valueOf(orderDO.getId()), String.valueOf(currentAmount.add(orderDO.getAmount())));
            List<Object> results = tx.exec();
            jedis.disconnect();
            if (results == null){
                throw new Exception("事务执行失败!");
            }
            logger.info("ID=>[" + orderDO.getId() + "] 新增Amount:[" + orderDO.getAmount() + "]成功!");
            return new ResponseDO();
        } catch (Exception e) {
            logger.error("Redis Transaction SET Exception!",e);
            return new ResponseDO(Errors.DEFAULT_ERROR);
        }
    }

    @Override
    public ResponseDO pipelined(List<OrderDO> orderDOList) {

        try {
            Jedis jedis = redisService.getJedis();
            Pipeline pipeline = jedis.pipelined();
            for (OrderDO orderDO : orderDOList) {
                String key = String.valueOf(orderDO.getId());
                String currentAmountStr = redisService.get(key);
                BigDecimal currentAmount = new BigDecimal(0.0);
                if (StringUtils.isNotBlank(currentAmountStr)){
                    currentAmount = new BigDecimal(currentAmountStr);
                }
                pipeline.set(key, String.valueOf(currentAmount.add(orderDO.getAmount())));
            }
            // syncAndReturnAll返回所有执行结果;sync不返回结果
            List<Object> results = pipeline.syncAndReturnAll();
            jedis.disconnect();
            if (org.apache.commons.collections.CollectionUtils.isEmpty(results)){
                logger.error("pipeline执行未成功!");
                return new ResponseDO(Errors.DEFAULT_ERROR.getErrorCode(),"pipeline 执行未成功!");
            }
            if (results.size() != orderDOList.size()){
                logger.error("只执行成功了=>[" + results.size() + "]");
                return new ResponseDO(Errors.DEFAULT_ERROR.getErrorCode(),"pipeline部分执行失败!");
            }
            for (OrderDO orderDO : orderDOList) {
                logger.info("ID=>[" + orderDO.getId() + "]Pipelined调用成功!");
            }
            return new ResponseDO();
        } catch (Exception e) {
            logger.error("Redis管道接口调用异常!",e);
            return new ResponseDO(Errors.DEFAULT_ERROR.getErrorCode(),"Redis管道接口调用异常!");
        }
    }

    @Override
    public ResponseDO pipelineAndTransaction(List<OrderDO> orderDOList) {
        try {
            Jedis jedis = redisService.getJedis();
            Pipeline pipeline = jedis.pipelined();
            pipeline.multi();
            for (OrderDO orderDO : orderDOList) {
                String key = String.valueOf(orderDO.getId());
                String currentAmountStr = redisService.get(key);
                BigDecimal currentAmount = new BigDecimal(0.0);
                if (StringUtils.isNotBlank(currentAmountStr)){
                    currentAmount = new BigDecimal(currentAmountStr);
                }
                pipeline.set(key, String.valueOf(currentAmount.add(orderDO.getAmount())));
            }
            pipeline.exec();
            // syncAndReturnAll返回所有执行结果;sync不返回结果
            List<Object> results = pipeline.syncAndReturnAll();
            jedis.disconnect();
            if (results == null){
                return new ResponseDO(Errors.DEFAULT_ERROR.getErrorCode(),"管道配合事务接口调用异常!");
            }
            for (OrderDO orderDO : orderDOList) {
                logger.info("ID=>[" + orderDO.getId() + "] pipelineAndTransaction!");
            }
            return new ResponseDO();
        } catch (Exception e) {
            logger.error("Redis管道配合事务接口调用异常!",e);
            return new ResponseDO(Errors.DEFAULT_ERROR.getErrorCode(),"管道配合事务接口调用异常!");
        }
    }
}

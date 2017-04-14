package org.wsh.common.service.impl.retry;

import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.wsh.common.service.api.retry.SpringRetryService;
import org.wsh.common.util.date.DateUtil;
import org.wsh.common.util.logger.LoggerService;

import java.util.Date;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-04-14 16:18
 */
@Service("springRetryService")
public class SpringRetryServiceImpl extends LoggerService implements SpringRetryService{

    private int count = 0;

    @Retryable(value= {RemoteAccessException.class},maxAttempts = 3,backoff = @Backoff(delay = 5000l,multiplier = 1))
    public boolean addUser() throws Exception{
        logger.info("当前时间:" + DateUtil.parseDate(new Date(),DateUtil.YYYY_MM_DD_HH_MM_SS));
        count++;
        if (count%2 == 1){
            throw new RemoteAccessException("RPC调用异常");
        }
        return true;
    }

    @Recover
    public void retry(){
        logger.info("补偿时间:" + DateUtil.parseDate(new Date(),DateUtil.YYYY_MM_DD_HH_MM_SS));
    }
}

package org.wsh.common.cache.service;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.wsh.common.cache.queue.SNService;
import org.wsh.common.cache.queue.TaskQueueService;
import org.wsh.common.cache.queue.model.Producer;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-04-18 11:57
 */
@Service("producerService")
public class ProducerService {

    ExecutorService service = Executors.newCachedThreadPool();

    @Resource
    private TaskQueueService taskQueueService;

    @Resource
    private SNService snService;

    public void doCreateProduce(String user){

        long tradeNO = snService.getTradeNO();
        Producer p = new Producer(user, taskQueueService);
        service.submit(p);
        p.run();
    }
}

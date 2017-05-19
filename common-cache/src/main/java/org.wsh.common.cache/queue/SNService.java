package org.wsh.common.cache.queue;

import org.springframework.stereotype.Service;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-04-18 10:36
 */
@Service("snService")
public class SNService {

    private long tradeNO = 1;

    public synchronized long getTradeNO(){
        tradeNO++;
        return tradeNO;
    }
}

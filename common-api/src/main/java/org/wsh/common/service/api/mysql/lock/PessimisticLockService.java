package org.wsh.common.service.api.mysql.lock;

import org.wsh.common.model.basic.UserBasicDO;

/**
 * 悲观锁
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  使用查询SQL加入FOR UPDATE并借助事务实现悲观锁
 * since Date： 2016-12-22 11:12
 */
public interface PessimisticLockService {

    /**
     * 使用悲观锁更新数据
     * @param userBasicDO UserBasicDO
     * @return boolean
     * @throws Exception
     */
    public boolean updateForLock(UserBasicDO userBasicDO) throws Exception;
}

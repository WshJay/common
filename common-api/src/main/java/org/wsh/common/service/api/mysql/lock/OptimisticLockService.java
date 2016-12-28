package org.wsh.common.service.api.mysql.lock;

import org.springframework.stereotype.Service;
import org.wsh.common.model.basic.UserBasicDO;

/**
 * 乐观锁
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  使用版本号实现乐观锁
 * since Date： 2016-12-22 11:12
 */
@Service
public interface OptimisticLockService {

    /**
     * 使用乐观锁更新数据
     * @param userBasicDO UserBasicDO
     * @return boolean
     * @throws Exception
     */
    public boolean updateForLock(UserBasicDO userBasicDO) throws Exception;

}

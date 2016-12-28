package org.wsh.common.service.impl.mysql.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wsh.common.dao.UserBasicDAO;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.service.api.mysql.lock.OptimisticLockService;
import org.wsh.common.util.logger.LoggerService;
import javax.annotation.Resource;

import static org.wsh.common.enums.SessionKey.user;

/**
 * 乐观锁
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  使用版本号实现乐观锁
 * since Date： 2016-12-22 11:12
 */
@Service(value = "optimisticLockService")
public class OptimisticLockServiceImpl extends LoggerService implements OptimisticLockService{

    @Resource
    private UserBasicDAO userBasicDAO;

    @Override
    public boolean updateForLock(UserBasicDO userBasicDO) throws Exception {

        try {
            UserBasicDO oldUser = userBasicDAO.queryById(userBasicDO.getId());
            Assert.notNull(oldUser,"参数异常!");
            oldUser.setFaceUrl(userBasicDO.getFaceUrl());
            int result = userBasicDAO.updateUserBasic(oldUser);
            if (result < 1){
                throw new Exception("更新异常!");
            }
        } catch (Exception e) {
            logger.error("乐观锁更新异常!",e);
            throw new Exception("乐观锁更新异常!");
        }
        return true;
    }
}

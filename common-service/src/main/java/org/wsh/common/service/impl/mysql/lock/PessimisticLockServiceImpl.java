package org.wsh.common.service.impl.mysql.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wsh.common.dao.UserBasicDAO;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.service.api.mysql.lock.PessimisticLockService;
import org.wsh.common.util.logger.LoggerService;

/**
 * 悲观锁
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  使用查询SQL加入FOR UPDATE并借助事务实现悲观锁
 * since Date： 2016-12-22 11:12
 */
@Service(value = "pessimisticLockService")
public class PessimisticLockServiceImpl extends LoggerService implements PessimisticLockService {

    @Autowired
    private UserBasicDAO userBasicDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateForLock(UserBasicDO userBasicDO) throws Exception {

        try {
            Assert.notNull(userBasicDO,"参数为空!");
            Assert.notNull(userBasicDO.getId(),"缺少必要参数!");
            UserBasicDO user = userBasicDAO.selectById(userBasicDO.getId());
            Thread.sleep(10000);
            int result = userBasicDAO.updateUserFaceById(user.getId(),userBasicDO.getFaceUrl());
            if (result < 1){
                throw new Exception("更新异常!");
            }
        } catch (Exception e) {
            System.out.println(e);
            logger.error("悲观锁更新异常!",e);
            throw new Exception("悲观锁更新异常!");
        }
        return true;
    }
}

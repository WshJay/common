package org.wsh.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wsh.common.dao.UserBasicDAO;
import org.wsh.common.enums.expection.Errors;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.support.response.ResponseDO;
import org.wsh.common.util.logger.LoggerService;

/**
 * 使用Spring AOP Cglib动态代理调用接口
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  不实现接口
 * since Date： 2017-03-09 10:35
 */
@Service("cglibService")
public class CglibService extends LoggerService{

    @Autowired
    private UserBasicDAO userBasicDAO;

    public ResponseDO<UserBasicDO> queryById(Long id){

        UserBasicDO userBasicDO = userBasicDAO.queryById(id);
        if (userBasicDO == null){
            return new ResponseDO<>(Errors.USER_DOES_NOT_EXIST);
        }
        return new ResponseDO<>(userBasicDO);
    }
}

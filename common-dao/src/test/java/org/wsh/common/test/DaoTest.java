package org.wsh.common.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.dao.RoleDAO;
import org.wsh.common.dao.UserBasicDAO;
import org.wsh.common.model.basic.RoleDO;
import org.wsh.common.support.base.AbstractLogger;

import javax.annotation.Resource;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  数据持久层接口测试
 * since Date： 2016/9/29 13:55
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:common-bean.xml")
public class DaoTest extends AbstractLogger{

    @Resource
    private RoleDAO roleDAO;

    @Resource
    private UserBasicDAO userBasicDAO;

    @Test
    public void test(){

//        List<RoleDO> roleDOList = roleDAO.queryAll();
//        for (RoleDO roleDO : roleDOList) {
//           logger.info("roleName:" + roleDO.getRoleName());
//        }
//        addRole("秘书","MS",1L);

//        userBasicDAO.queryById(6L);
        userBasicDAO.updateUserFaceById(6L,"8");
    }

    /**
     * 添加一个角色
     * @param roleName
     * @param roleCode
     * @param createUserId
     * @return
     */
    public int addRole(String roleName, String roleCode, Long createUserId){

        RoleDO roleDO = new RoleDO();
        roleDO.setRoleName(roleName);
        roleDO.setRoleCode(roleCode);
        roleDO.setCreateUserId(createUserId);
        return roleDAO.insertRole(roleDO);
    }
}


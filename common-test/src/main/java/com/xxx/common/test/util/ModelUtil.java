package com.xxx.common.test.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xxx.common.test.model.dto.RoleDO;
import com.xxx.common.test.model.dto.UserBasicDO;
import com.xxx.common.test.model.vo.RoleVO;
import com.xxx.common.test.model.vo.UserBasicVO;

public class ModelUtil {
	
	private final Logger log = LoggerFactory.getLogger(ModelUtil.class);

	@Test
	public void test(){
		commonsBeanUtilTest();
		springBeanUtilTest();
	}
	
	private void springBeanUtilTest() {
			List<UserBasicDO> userDOList = new ArrayList<UserBasicDO>();
			UserBasicDO userBasicDO = new UserBasicDO();
			userBasicDO.setId(1l);
			userBasicDO.setUserName("wsh");
			userBasicDO.setPassword("123456");
			userBasicDO.setSalt("123");
			userBasicDO.setPhone("15558053971");
			userBasicDO.setEmail("675783578@qq.com");
			userBasicDO.setRealName("王");
			userBasicDO.setStatus(1);
			
			List<RoleDO> roleDOList = new ArrayList<RoleDO>();
			RoleDO roleDO = new RoleDO();
			roleDO.setId(1l);
			roleDO.setRoleCode("admin");
			roleDOList.add(roleDO);
			userBasicDO.setRoleList(roleDOList);
			
			userDOList.add(userBasicDO);
			UserBasicDO userBasic1DO = new UserBasicDO();
			userBasic1DO.setId(2l);
			userBasic1DO.setUserName("wsh1");
			userBasic1DO.setPassword("1234561");
			userBasic1DO.setSalt("1231");
			userBasic1DO.setPhone("155580539711");
			userBasic1DO.setEmail("6757835781@qq.com");
			userBasic1DO.setRealName("王1");
			userBasic1DO.setStatus(2);
			userDOList.add(userBasic1DO);
			UserBasicVO userBasicVO = new UserBasicVO();
			List<UserBasicVO> userBasicVOList = new ArrayList<UserBasicVO>();
			org.springframework.beans.BeanUtils.copyProperties(userBasicDO, userBasicVO);
			log.info("id:" + userBasicVO.getId() + " " + userBasicVO.getEmail());
			userBasicVO.getRoleList();
			for (RoleDO roleDO1 : userBasicVO.getRoleList()) {
				log.info(roleDO1.getRoleCode());
			}
	}
	

	private void commonsBeanUtilTest() {
		try {
			List<UserBasicDO> userDOList = new ArrayList<UserBasicDO>();
			UserBasicDO userBasicDO = new UserBasicDO();
			userBasicDO.setId(1l);
			userBasicDO.setUserName("wsh");
			userBasicDO.setPassword("123456");
			userBasicDO.setSalt("123");
			userBasicDO.setPhone("15558053971");
			userBasicDO.setEmail("675783578@qq.com");
			userBasicDO.setRealName("王");
			userBasicDO.setStatus(1);
			
			List<RoleDO> roleDOList = new ArrayList<RoleDO>();
			RoleDO roleDO = new RoleDO();
			roleDO.setId(1l);
			roleDO.setRoleCode("admin");
			roleDOList.add(roleDO);
			userBasicDO.setRoleList(roleDOList);
			
			userDOList.add(userBasicDO);
			UserBasicDO userBasic1DO = new UserBasicDO();
			userBasic1DO.setId(2l);
			userBasic1DO.setUserName("wsh1");
			userBasic1DO.setPassword("1234561");
			userBasic1DO.setSalt("1231");
			userBasic1DO.setPhone("155580539711");
			userBasic1DO.setEmail("6757835781@qq.com");
			userBasic1DO.setRealName("王1");
			userBasic1DO.setStatus(2);
			userDOList.add(userBasic1DO);
			UserBasicVO userBasicVO = new UserBasicVO();
			List<UserBasicVO> userBasicVOList = new ArrayList<UserBasicVO>();
			BeanUtils.copyProperties(userBasicVO, userBasicDO);
			String[] str = BeanUtils.getArrayProperty(userBasicDO, "id");
			for (String string : str) {
				log.info(string);
			}
			log.info("id:" + userBasicVO.getId() + " " + userBasicVO.getEmail());
			userBasicVO.getRoleList();
			for (RoleDO roleDO1 : userBasicVO.getRoleList()) {
				log.info(roleDO1.getRoleCode());
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}


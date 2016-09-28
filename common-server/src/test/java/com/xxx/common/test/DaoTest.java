package com.xxx.common.test;

import com.xxx.common.repository.dao.*;
import com.xxx.common.repository.entity.MenuDO;
import com.xxx.common.repository.entity.RoleDO;
import com.xxx.common.repository.entity.UserBasicDO;
import com.xxx.common.repository.entity.UserRoleDO;
import com.xxx.common.repository.entity.app.user.AppUserInfo;
import com.xxx.common.util.exception.BusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/dal.xml"})
public class DaoTest {
	
	@Autowired
	private UserBasicDAO userBasicDAO;
	
	@Autowired
	private UserRoleDAO userRoleDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private PermissionDAO permissionDAO;
	
	@Autowired
	private MenuDAO menuDAO;
	
	private final Logger log = LoggerFactory.getLogger(DaoTest.class);
	
	@Test()
	public void test(){
//		Long userId;
//		try {
//			userId = addUser("管理员");
//		} catch (BusinessException e) {
//			log.warn("添加用戶異常",e);
//		}
//		log.info("userId:" + userId);
		
//		addRole("管理员", "admin", 1l);
//		addRole("客服", "costomServer", 1l);
//		addRole("产品", "product", 1l);
//		UserBasicDO user = userBasicDAO.queryByUserName("管理员");
//		if (user != null) {
//			// 给用户设置权限
//			addUserRoleDO(user.getId(), 1l, 1l);
//			addUserRoleDO(user.getId(), 2l, 1l);
//		}
//		addPermission("YHGL", "用户管理", 1l);
		
//		List<PermissionDO> permissionList = permissionDAO.queryListByRoleId(1L);
//		for (PermissionDO permissionDO : permissionList) {
//			log.info(permissionDO.getName());
//		}
		
//		List<MenuDO> menuList = menuDAO.queryAllChildrenMenu();
		Set<Integer> userIdSet = new HashSet<Integer>();
		userIdSet.add(0);
		userIdSet.add(1);

	}
	
	public <T> Map<String, T> covertMap(String key, List<T> list) {
		Map<String,T> map = new HashMap<String,T>();
		for (T t : list) {
			try {
				Field field = t.getClass().getDeclaredField(key);
				field.setAccessible(true); // 设置些属性是可以访问的
				map.put(field.get(t).toString(), t);
			} catch (NoSuchFieldException e) {
				log.error("List转Map异常",e);
			} catch (SecurityException e) {
				log.error("List转Map异常",e);
			} catch (IllegalArgumentException e) {
				log.error("List转Map异常",e);
			} catch (IllegalAccessException e) {
				log.error("List转Map异常",e);
			}
		}
		return map;
	}
	
	public Long addUserRoleDO(Long userId, Long roleId, Long createUserId){
		if (userId > 0 && roleId > 0 && createUserId > 0) {
			UserRoleDO userRoleDO = new UserRoleDO();
			userRoleDO.setUserId(userId);
			userRoleDO.setRoleId(roleId);
			userRoleDO.setCreateUserId(createUserId);
			return userRoleDAO.insertUserRole(userRoleDO);
		}
		return 0l;
		
	}
	
	/**
	 * 添加一个用户
	 * @return
	 */
	public Long addUser(String userName) throws BusinessException{
		Long result = 0l;
		UserBasicDO userBasicDO = new UserBasicDO();
		userBasicDO.setUserName(userName);
		//			HashPassword hashPassword = HashPwd.encrypt("123456");
//			userBasicDO.setPassword(hashPassword.password);
//			userBasicDO.setSalt(hashPassword.salt);
//			userBasicDO.setPhone("15558053971");
//			userBasicDO.setEmail("675783578@qq.com");
//			userBasicDO.setRealName("wsh");
//			userBasicDO.setStatus(0);
		userBasicDAO.insertUserBasic(userBasicDO);
		return result;
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
	
	/**
	 * 添加一个权限信息
	 * @param code
	 * @param description
	 * @param createUserId
	 * @return
	 */
//	public int addPermission(String code, String name, Long createUserId){
//		
//		PermissionDO permissionDO = new PermissionDO();
//		permissionDO.setCode(code);
//		permissionDO.setName(name);
//		permissionDO.setCreateUserId(createUserId);
//		return permissionDAO.insertPermission(permissionDO);
//	}

	@Test
	public void collectionTest(){
		List<MenuDO> menuDOList = menuDAO.queryAllChildrenMenu();
		System.out.println(menuDOList.size());
	}

}


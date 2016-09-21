package com.xxx.common.server.service.impl;

import static com.xxx.common.server.util.BeansUtil.newOptionsResponseDO;
import static com.xxx.common.server.util.BeansUtil.newResponseDO;
import static com.xxx.common.server.util.BeansUtil.newStaticOptionsResponseDO;
import static com.xxx.common.server.util.BeansUtil.newStaticResponseDO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.xxx.common.repository.dao.PermissionDAO;
import com.xxx.common.repository.dao.RoleDAO;
import com.xxx.common.repository.dao.UserBasicDAO;
import com.xxx.common.repository.dao.UserRoleDAO;
import com.xxx.common.repository.data.beans.OptionsResponseDO;
import com.xxx.common.repository.data.enums.Errors;
import com.xxx.common.repository.data.support.ResponseDO;
import com.xxx.common.repository.entity.PermissionDO;
import com.xxx.common.repository.entity.RoleDO;
import com.xxx.common.repository.entity.UserBasicDO;
import com.xxx.common.repository.entity.UserRoleDO;
import com.xxx.common.repository.pagination.Pagination;
import com.xxx.common.server.service.UserService;
import com.xxx.common.util.exception.BusinessException;
import com.xxx.common.util.shiro.encrypt.HashPwd;
import com.xxx.common.util.shiro.encrypt.HashPwd.HashPassword;

/**
 * 用户服务层实现类
 * File Name: <UserServiceImpl.java>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-4-27 下午4:00:07
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserBasicDAO userBasicDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private PermissionDAO permissionDAO;
	
	@Autowired
	private UserRoleDAO userRoleDAO;
	
	private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public List<UserBasicDO> getAllUserBasicList() {
		return userBasicDAO.queryALL();
	}
	
	@Override
	public UserBasicDO getUserBasicByUserName(String userName) {
		return userBasicDAO.queryByUserName(userName);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDO doDelUserById(Long userId)throws BusinessException {
		try {
			UserBasicDO userBasicDO = userBasicDAO.queryById(userId);
			if (userBasicDO == null) {
				return newResponseDO(Errors.PARAMETER_IS_ERROR);
			}
			userBasicDAO.deleteById(userId);
		} catch (Exception e) {
			throw new BusinessException(String.format("根据用户ID:[%S]删除用户异常", userId));
		}
		return newStaticResponseDO();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserBasicDO> getUserList(UserBasicDO userBasicDO,
			Pagination pagination) {
//		int totalCount = userBasicDAO.queryCountByParams(userBasicDO);
//		if (totalCount <= 0) {
//			return Collections.EMPTY_LIST;
//		}
//		pagination.setTotalCount(totalCount);
//		List<UserBasicDO> userList = userBasicDAO.queryByParams(userBasicDO, pagination.getRowBounds());
//		return userList;
		return null;
	}

	@Override
	public UserBasicDO addUser(UserBasicDO userBasicDO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserBasicDO getUserByUserId(Long userId) throws BusinessException{
		try {
			return userBasicDAO.queryById(userId);
		} catch (Exception e) {
			throw new BusinessException(String.format("根据用户ID查询用户异常,参数userId:[%s]", userId));
		}
	}

	@Override
	public UserBasicDO getUserRoleByUserName(String userName){
		UserBasicDO user = userBasicDAO.queryByUserName(userName);
		List<RoleDO> roleList = roleDAO.queryListByUserId(user.getId());
		if (CollectionUtils.isEmpty(roleList)) {
			return user;
		}
		user.setRoleList(roleList);
		// 此用户只有一个角色
		if (roleList.size() == 1) {
			List<PermissionDO> pList = permissionDAO.queryListByRoleId(roleList.get(0).getId());
			user.setPermissionSet(new HashSet<PermissionDO>(pList));
		}else{
			List<PermissionDO> permissonList = new ArrayList<PermissionDO>();
			for (RoleDO roleDO : roleList) {
				List<PermissionDO> pList = permissionDAO.queryListByRoleId(roleDO.getId());
				permissonList.addAll(pList);
			}
			if (CollectionUtils.isEmpty(permissonList)) {
				return user;
			}
			// 去除多余权限
			Set<PermissionDO> permissionSet = new HashSet<PermissionDO>(permissonList);
			user.setPermissionSet(permissionSet);
		}
		
		return user;
	}
	

	@Override
	public UserBasicDO getUserRoleByUserId(Long userId)
			throws BusinessException {
		try {
			UserBasicDO user = userBasicDAO.queryById(userId);
			List<RoleDO> roleList = roleDAO.queryListByUserId(user.getId());
			if (CollectionUtils.isEmpty(roleList)) {
				return user;
			}
			user.setRoleList(roleList);
			// 此用户只有一个角色
			if (roleList.size() == 1) {
				List<PermissionDO> pList = permissionDAO.queryListByRoleId(roleList.get(0).getId());
				user.setPermissionSet(new HashSet<PermissionDO>(pList));
			}else{
				List<PermissionDO> permissonList = new ArrayList<PermissionDO>();
				for (RoleDO roleDO : roleList) {
					List<PermissionDO> pList = permissionDAO.queryListByRoleId(roleDO.getId());
					permissonList.addAll(pList);
				}
				if (CollectionUtils.isEmpty(permissonList)) {
					return user;
				}
				// 去除多余权限
				Set<PermissionDO> permissionSet = new HashSet<PermissionDO>(permissonList);
				user.setPermissionSet(permissionSet);
			}
			return user;
		} catch (Exception e) {
			throw new BusinessException(String.format("根据用户ID:[%s]查询用户角色权限信息异常", userId),e);
		}
		
	}

	@Override
	public List<RoleDO> getRoleList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUserRole(UserRoleDO userRoleDO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserBasicDO> getUserRoleByRole(RoleDO roleDO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addUser(String userName, String password, String realName,
			String phone, String email) {
		// TODO TO Validator
		UserBasicDO userBasicDO = new UserBasicDO();
		userBasicDO.setUserName(userName);
		HashPassword hashPassword = HashPwd.encrypt(password);
		userBasicDO.setPassword(hashPassword.password);
		userBasicDO.setSalt(hashPassword.salt);
		userBasicDO.setPhone(phone);
		userBasicDO.setEmail(email);
		userBasicDO.setRealName(realName);
		userBasicDO.setStatus(0);
		try {
			userBasicDAO.insertUserBasic(userBasicDO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDO doUpdateUserById(Long userId, String realName, String phone,
			String email) throws BusinessException{
		try {
			// TODO validate
			UserBasicDO userBasicDO = userBasicDAO.queryById(userId);
			if (userBasicDO == null) {
				return newResponseDO(Errors.PARAMETER_IS_ERROR);
			}
			userBasicDO.setPhone(phone);
			userBasicDO.setEmail(email);
			userBasicDO.setRealName(realName);
			userBasicDAO.updateUserBasic(userBasicDO);
		} catch (Exception e) {
			throw new BusinessException(String.format("根据id:[%s],realName:[%s]phone:[%s]email:[%s],更新用戶信息异常", userId,realName,realName,phone,email), e);
		}
		return newStaticResponseDO();
	}

	@Override
	public List<UserBasicDO> getUserListByParams(String userName,
			String realName, String phone, String email, Pagination pagination) {
		
//		int totalCount = userBasicDAO.queryCountByParams(userBasicDO);
//		if (totalCount <= 0) {
//			return Collections.EMPTY_LIST;
//		}
//		pagination.setTotalCount(totalCount);
//		List<UserBasicDO> userList = userBasicDAO.queryByParams(userBasicDO, pagination.getRowBounds());
//		return userList;
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public OptionsResponseDO<List<UserBasicDO>> getUserListByParams(UserBasicDO userBasicDO, Pagination pagination) throws BusinessException{
		try {
			int totalCount = userBasicDAO.queryCountByParams(userBasicDO);
			if (totalCount <= 0) {
				return newStaticOptionsResponseDO();
			}
			pagination.setTotalCount(totalCount);
			List<UserBasicDO> userList = userBasicDAO.queryByParams(userBasicDO, pagination.getRowBounds());
			return newOptionsResponseDO(totalCount, userList, pagination.getPageSize(),pagination.getPP());
		} catch (Exception e) {
			throw new BusinessException("多条件查询用户信息异常",e);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDO doAddUser(Long currentUserId, String userName, String password,
			String realName, String phone, String email, Long roleId) throws BusinessException{
		try {
			// TODO TO Validator
			UserBasicDO userBasicDO = new UserBasicDO();
			userBasicDO.setUserName(userName);
			HashPassword hashPassword = HashPwd.encrypt(password);
			userBasicDO.setPassword(hashPassword.password);
			userBasicDO.setSalt(hashPassword.salt);
			userBasicDO.setPhone(phone);
			userBasicDO.setEmail(email);
			userBasicDO.setRealName(realName);
			userBasicDO.setStatus(0);
			userBasicDAO.insertUserBasic(userBasicDO);
			UserRoleDO userRoleDO = new UserRoleDO();
			userRoleDO.setUserId(userBasicDO.getId());
			userRoleDO.setRoleId(roleId);
			userRoleDO.setCreateUserId(currentUserId);
			userRoleDAO.insertUserRole(userRoleDO);
		} catch (Exception e) {
			throw new BusinessException(String.format("添加用户信息异常，参数currentUserId:[%s],userName:[%s],password:[%s],realName:[%s],phone:[%s],email:[%s],roleId:[%s],", currentUserId,userName,password,realName,phone,email,roleId), e);
		}
		return newStaticResponseDO();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDO doUpdateUser(Long currentUserId, Long userId, String realName, String phone,
			String email, Long roleId) throws BusinessException {
		try {
			// TODO validate
			UserBasicDO userBasicDO = userBasicDAO.queryById(userId);
			if (userBasicDO == null) {
				return newResponseDO(Errors.PARAMETER_IS_ERROR);
			}
			userBasicDO.setPhone(phone);
			userBasicDO.setEmail(email);
			userBasicDO.setRealName(realName);
			userBasicDAO.updateUserBasic(userBasicDO);
			UserRoleDO userRoleDO = new UserRoleDO();
			userRoleDO.setUserId(userBasicDO.getId());
			userRoleDO.setRoleId(roleId);
			userRoleDO.setCreateUserId(currentUserId);
			userRoleDAO.deleteByUserId(userBasicDO.getId());
			userRoleDAO.insertUserRole(userRoleDO);
		} catch (Exception e) {
			throw new BusinessException(String.format("根据currentUserId:[%s],userId:[%s],realName:[%s]phone:[%s]email:[%s]roleId:[%s],更新用戶信息异常", currentUserId,userId,realName,realName,phone,email,roleId), e);
		}
		return newStaticResponseDO();
	}

}


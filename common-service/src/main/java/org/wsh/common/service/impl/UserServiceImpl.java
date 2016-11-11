package org.wsh.common.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.wsh.common.service.api.UserService;
import org.wsh.common.dao.PermissionDAO;
import org.wsh.common.dao.RoleDAO;
import org.wsh.common.dao.UserBasicDAO;
import org.wsh.common.dao.UserRoleDAO;
import org.wsh.common.enums.expection.Errors;
import org.wsh.common.model.basic.PermissionDO;
import org.wsh.common.model.basic.RoleDO;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.model.basic.UserRoleDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;
import org.wsh.common.util.shiro.encrypt.HashPwd;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.wsh.common.support.beans.BeansUtil.*;

/**
 * 用户服务层实现类
 * File Name: <UserServiceImpl.java>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-4-27 下午4:00:07
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
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
	public int updateUserFaceUrl(String faceUrl, Long id) {
		return 0;
	}

	@Override
	public int addUser(String userName, String password, String realName,
			String phone, String email) {
		// TODO TO Validator
		UserBasicDO userBasicDO = new UserBasicDO();
		userBasicDO.setUserName(userName);
		HashPwd.HashPassword hashPassword = HashPwd.encrypt(password);
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
			HashPwd.HashPassword hashPassword = HashPwd.encrypt(password);
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


package org.wsh.common.service.api;

import java.util.List;
import org.springframework.stereotype.Service;
import org.wsh.common.model.basic.RoleDO;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.model.basic.UserRoleDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;

/**
 * 用户服务层
 * File Name: <UserService.java>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-6-4 上午8:40:25
 */
@Service
public interface UserService {
	
	/**
	 * 获取所有用户信息
	 * @return
	 */
	public List<UserBasicDO> getAllUserBasicList();

	/**
	 * 多条件查询用户（分页）
	 * @return
	 */
	public List<UserBasicDO> getUserList(UserBasicDO userBasicDO, Pagination pagination);
	
	/**
	 * 多条件查询用户（分页）
	 * @param userBasicDO
	 * @return
	 */
	public OptionsResponseDO<List<UserBasicDO>> getUserListByParams(UserBasicDO userBasicDO, Pagination pagination)throws BusinessException;
	
	/**
	 * 多条件查询用户（分页）
	 * @param userName
	 * @param realName
	 * @param phone
	 * @param email
	 * @param pagination
	 * @return
	 */
	public List<UserBasicDO> getUserListByParams(String userName, String realName, String phone, String email, Pagination pagination);
	
	/**
	 * 根据用户名查询用户
	 * @param userName
	 * @return
	 */
	public UserBasicDO getUserBasicByUserName(String userName);
	
	/**
	 * 添加用户
	 * @param userBasicDO
	 * @return
	 */
	@Deprecated
	public UserBasicDO addUser(UserBasicDO userBasicDO);
	
	/**
	 * 添加用户
	 * @param userName
	 * @param password
	 * @param realName
	 * @param phone
	 * @param email
	 * @return
	 */
	public int addUser(String userName, String password, String realName, String phone, String email);
	
	/**
	 * 添加用户
	 * @param currentUserId
	 * @param userName
	 * @param password
	 * @param realName
	 * @param phone
	 * @param email
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResponseDO doAddUser(Long currentUserId, String userName, String password, String realName, String phone, String email, Long roleId) throws BusinessException;
	
	/**
	 * 修改用户基本信息
	 * @param userId
	 * @param realName
	 * @param phone
	 * @param email
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResponseDO doUpdateUserById(Long userId, String realName, String phone, String email) throws BusinessException;
	
	/**
	 * 修改用户信息
	 * @param userId
	 * @param realName
	 * @param phone
	 * @param email
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResponseDO doUpdateUser(Long currentUserId, Long userId, String realName, String phone, String email, Long roleId) throws BusinessException;
	
	/**
	 * 根据用户ID删除用户
	 * @param userId
	 */
	@SuppressWarnings("rawtypes")
	public ResponseDO doDelUserById(Long userId) throws BusinessException;
	
	/**
	 * 根据用户ID查询用户
	 * @param UserName
	 * @return
	 */
	public UserBasicDO getUserByUserId(Long userId) throws BusinessException;
	
	/**
	 * 根据用户名查询用户权限列表
	 * @param userName
	 * @return
	 */
	public UserBasicDO getUserRoleByUserName(String userName);
	
	/**
	 * 根据用户ID查询用户角色权限信息
	 * @param userName
	 * @return
	 */
	public UserBasicDO getUserRoleByUserId(Long userId)throws BusinessException;
	
	/**
	 * 获取所有用户角色
	 * @return
	 */
	public List<RoleDO> getRoleList();
	
	/**
	 * 更新用户角色关系
	 * @param userRole
	 */
	public void updateUserRole(UserRoleDO userRoleDO);
	
	/**
	 * 根据角色信息查询用户列表
	 * @param role 用户角色信息
	 * @return
	 */
	public List<UserBasicDO> getUserRoleByRole(RoleDO roleDO);

	/**
	 * 根据用户ID更新头像地址
	 * @param faceUrl
	 * @param id
     * @return
     */
	public int updateUserFaceUrl(String faceUrl, Long id);
	
}


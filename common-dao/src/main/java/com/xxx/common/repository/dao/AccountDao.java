package com.xxx.common.repository.dao;

import java.util.List;

import com.xxx.common.repository.base.SqlMapper;
import com.xxx.common.repository.entity.Role;
import com.xxx.common.repository.entity.User;


/**
 * 用户信息查询
 * 
 * CopyRright (c) 2014:   <般豆网络>
 * Project:     <mercury-repository>
 * File Name:   <AccountDao.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-3-26 下午6:35:28
 */
public interface AccountDao extends SqlMapper{

	/**
	 * 根据用户名查询用户权限列表
	 * @param userName 用户名
	 * @return
	 */
	User getUserRoleByUserName(String userName);
	
	/**
	 * 根据角色信息查询用户列表 
	 * @param role 角色
	 * @return
	 */
	List<User> queryUserRoleByRole(Role role);
	
}


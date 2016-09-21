package com.xxx.common.repository.dao;

import java.util.List;
import org.apache.ibatis.session.RowBounds;
import com.xxx.common.repository.base.SqlMapper;
import com.xxx.common.repository.entity.User;


/**
 * User Dao层
 * 
 * CopyRright (c) 2014:   <般豆网络>
 * Project:     <order_food>
 * File Name:   <UserDao.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-3-14 下午4:11:42
 */
public interface UserDao extends SqlMapper{

	/**
	 * 查询所有用户
	 * @return
	 */
	List<User> getUserList();
	
	/**
	 * 多条件查询用户(分页)
	 * @return
	 */
	List<User> queryUserListByInfo(User user, RowBounds rowBounds);
	
	int queryUserCountByInfo(User user);
	
	/**
	 * 根据用户名查询用户
	 * @param userName
	 * @return
	 */
	User getUserByUserName(String userName);
	
	/**
	 * 根据用户ID查询用户
	 * @param userId
	 * @return
	 */
	User getUserByUserId(Long userId);
	
	/**
	 * 添加用户
	 * @param user
	 */
	void addUser(User user);
	
	/**
	 * 更新用户
	 * @param user
	 */
	void updateUser(User user);
	
	/**
	 * 删除用户
	 * @param user
	 */
	void deleteUser(User user);
	
}


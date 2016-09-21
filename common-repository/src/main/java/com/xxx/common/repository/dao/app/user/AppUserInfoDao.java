package com.xxx.common.repository.dao.app.user ;

import com.xxx.common.repository.base.SqlMapper;
import com.xxx.common.repository.entity.app.user.AppUserInfo;
import com.xxx.common.repository.entity.base.SimpleMapDO;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserInfoDao extends SqlMapper {
	
	/**
	 * 根据ID查询表数据
	 * @param id
	 * @return
	 */
	public AppUserInfo loadById(int id);
	
	/**
	 * 插入信息
	 * @param appUserInfo 
	 * @return
	 */
	int insert(AppUserInfo appUserInfo);
	
	/**
	 * 根据ID删除信息
	 * @param id
	 */
	void deleteById(int id);
	
	/**
	 * 批量添加信息
	 * @param appUserInfo List
	 */
	void insertList(List<AppUserInfo> appUserInfoList);
	
	/**
	 * 多条件查询用户信息
	 * @param appUserInfo
	 * @param rowBounds 
	 * @return
	 */
	List<AppUserInfo> queryListByParams(AppUserInfo appUserInfo, RowBounds rowBounds);
	int queryCountByParams(AppUserInfo appUserInfo);
	
	/**
	 * 根据用户Ids查询用户列表
	 * @param userIdList
	 * @return
	 */
	List<AppUserInfo> queryListByUserIdList(List<Integer> userIdList);
	
	/**
	 * 根据时间范围查询每日注册用户数量
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	List<SimpleMapDO> queryRegistNumByTimeFrame(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
}

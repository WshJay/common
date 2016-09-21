package com.xxx.common.repository.dao.app.user ;

import org.springframework.stereotype.Repository;

import com.xxx.common.repository.base.SqlMapper;
import com.xxx.common.repository.entity.app.user.AppUserLoginRecord;

@Repository
public interface AppUserLoginRecordDao extends SqlMapper {
	
	/**
	 * 根据ID查询表数据
	 * @param id
	 * @return
	 */
	public AppUserLoginRecord loadById(int id);
	
	/**
	 * 插入信息
	 * @param appUserLoginRecord 
	 * @return
	 */
	int insert(AppUserLoginRecord appUserLoginRecord);
	
	/**
	 * 根据ID删除信息
	 * @param id
	 */
	void deleteById(int id);
	
	int insertUserLogin(AppUserLoginRecord appUserLoginRecord);
	
}

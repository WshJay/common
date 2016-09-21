package com.xxx.common.repository.dao.app.system ;

import org.springframework.stereotype.Repository;

import com.xxx.common.repository.base.SqlMapper;
import com.xxx.common.repository.entity.app.system.AppBannerClick;

@Repository
public interface AppBannerClickDao extends SqlMapper{
	
	/**
	 * 根据ID查询表数据
	 * @param id
	 * @return
	 */
	public AppBannerClick loadById(int id);
	
	/**
	 * 插入信息
	 * @param appBannerClick 
	 * @return
	 */
	int insert(AppBannerClick appBannerClick);
	
	/**
	 * 根据ID删除信息
	 * @param id
	 */
	void deleteById(int id);
	
}

package com.xxx.common.repository.dao.data ;

import com.xxx.common.repository.entity.data.TjPartyClick;
import com.xxx.common.repository.base.SqlMapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TjPartyClickDao extends SqlMapper {
	
	/**
	 * 根据ID查询表数据
	 * @param id
	 * @return
	 */
	public TjPartyClick loadById(int id);
	
	/**
	 * 插入信息
	 * @param tjPartyClick 
	 * @return
	 */
	int insert(TjPartyClick tjPartyClick);
	
	/**
	 * 根据ID删除信息
	 * @param id
	 */
	void deleteById(int id);
	
	/**
	 * 批量添加信息
	 * @param tjPartyClick List
	 */
	void insertList(List<TjPartyClick> tjPartyClickList);
	
	/**
	 * 根据时间区间查询列表数据
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	List<TjPartyClick> queryListByTime(@Param("beginTime") String beginTime,@Param("endTime") String endTime);
	
}

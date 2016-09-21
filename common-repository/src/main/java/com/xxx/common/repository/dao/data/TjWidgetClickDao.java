package com.xxx.common.repository.dao.data ;

import com.xxx.common.repository.entity.data.TjWidgetClick;
import com.xxx.common.repository.base.SqlMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TjWidgetClickDao extends SqlMapper {
	
	/**
	 * 根据ID查询表数据
	 * @param id
	 * @return
	 */
	public TjWidgetClick loadById(int id);
	
	/**
	 * 插入信息
	 * @param tjWidgetClick 
	 * @return
	 */
	int insert(TjWidgetClick tjWidgetClick);
	
	/**
	 * 根据ID删除信息
	 * @param id
	 */
	void deleteById(int id);
	
	/**
	 * 批量添加信息
	 * @param tjWidgetClick List
	 */
	void insertList(List<TjWidgetClick> tjWidgetClickList);
	
}

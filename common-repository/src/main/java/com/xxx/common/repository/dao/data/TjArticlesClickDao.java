package com.xxx.common.repository.dao.data ;

import com.xxx.common.repository.entity.data.TjArticlesClick;
import com.xxx.common.repository.base.SqlMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TjArticlesClickDao extends SqlMapper {
	
	/**
	 * 根据ID查询表数据
	 * @param id
	 * @return
	 */
	public TjArticlesClick loadById(int id);
	
	/**
	 * 插入信息
	 * @param tjArticlesClick 
	 * @return
	 */
	int insert(TjArticlesClick tjArticlesClick);
	
	/**
	 * 根据ID删除信息
	 * @param id
	 */
	void deleteById(int id);
	
	/**
	 * 批量添加信息
	 * @param tjArticlesClick List
	 */
	void insertList(List<TjArticlesClick> tjArticlesClickList);
	
}

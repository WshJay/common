package com.xxx.common.repository.dao.data ;

import com.xxx.common.repository.entity.data.TjPlayClick;
import com.xxx.common.repository.base.SqlMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TjPlayClickDao extends SqlMapper {
	
	/**
	 * 根据ID查询表数据
	 * @param id
	 * @return
	 */
	public TjPlayClick loadById(int id);
	
	/**
	 * 插入信息
	 * @param tjPlayClick 
	 * @return
	 */
	int insert(TjPlayClick tjPlayClick);
	
	/**
	 * 根据ID删除信息
	 * @param id
	 */
	void deleteById(int id);
	
	/**
	 * 批量添加信息
	 * @param tjPlayClick List
	 */
	void insertList(List<TjPlayClick> tjPlayClickList);
	
}

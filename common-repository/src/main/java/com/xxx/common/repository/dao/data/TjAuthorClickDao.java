package com.xxx.common.repository.dao.data ;

import com.xxx.common.repository.entity.data.TjAuthorClick;
import com.xxx.common.repository.base.SqlMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TjAuthorClickDao extends SqlMapper {
	
	/**
	 * 根据ID查询表数据
	 * @param id
	 * @return
	 */
	public TjAuthorClick loadById(int id);
	
	/**
	 * 插入信息
	 * @param tjAuthorClick 
	 * @return
	 */
	int insert(TjAuthorClick tjAuthorClick);
	
	/**
	 * 根据ID删除信息
	 * @param id
	 */
	void deleteById(int id);
	
	/**
	 * 批量添加信息
	 * @param tjAuthorClick List
	 */
	void insertList(List<TjAuthorClick> tjAuthorClickList);
	
}

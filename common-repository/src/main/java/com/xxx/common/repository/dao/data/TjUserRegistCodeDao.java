package com.xxx.common.repository.dao.data ;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xxx.common.repository.base.SqlMapper;
import com.xxx.common.repository.entity.data.TjUserRegistCode;
import com.xxx.common.repository.entity.data.TjUserStay;

@Repository
public interface TjUserRegistCodeDao extends SqlMapper {
	
	/**
	 * 根据ID查询表数据
	 * @param id
	 * @return
	 */
	public TjUserRegistCode loadById(int id);
	
	/**
	 * 插入信息
	 * @param tjUserRegistCode 
	 * @return
	 */
	int insert(TjUserRegistCode tjUserRegistCode);
	
	/**
	 * 批量添加信息
	 * @param tjUserRegistCode List
	 */
	void insertList(List<TjUserRegistCode> tjUserRegistCode);
	
	/**
	 * 根据ID删除信息
	 * @param id
	 */
	void deleteById(int id);
	
}

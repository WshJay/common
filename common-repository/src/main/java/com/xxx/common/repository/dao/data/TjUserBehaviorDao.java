package com.xxx.common.repository.dao.data ;

import com.xxx.common.repository.entity.data.TjUserBehavior;
import com.xxx.common.repository.base.SqlMapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TjUserBehaviorDao extends SqlMapper {
	
	/**
	 * 根据ID查询表数据
	 * @param id
	 * @return
	 */
	public TjUserBehavior loadById(int id);
	
	/**
	 * 插入信息
	 * @param tjUserBehavior 
	 * @return
	 */
	int insert(TjUserBehavior tjUserBehavior);
	
	/**
	 * 根据ID删除信息
	 * @param id
	 */
	void deleteById(int id);
	
	/**
	 * 批量添加信息
	 * @param tjUserBehavior List
	 */
	void insertList(List<TjUserBehavior> tjUserBehaviorList);
	
	void updateBehavior(@Param("behaviorId") int behaviorId);
	
}

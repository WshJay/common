package com.xxx.common.repository.dao.data ;

import com.xxx.common.repository.entity.base.SimpleMapDO;
import com.xxx.common.repository.entity.data.TjButtonClick;
import com.xxx.common.repository.entity.data.dto.TjButtonClickTimes;
import com.xxx.common.repository.base.SqlMapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface TjButtonClickDao extends SqlMapper {
	
	/**
	 * 根据ID查询表数据
	 * @param id
	 * @return
	 */
	public TjButtonClick loadById(int id);
	
	/**
	 * 插入信息
	 * @param tjButtonClick 
	 * @return
	 */
	int insert(TjButtonClick tjButtonClick);
	
	/**
	 * 根据ID删除信息
	 * @param id
	 */
	void deleteById(int id);
	
	/**
	 * 批量添加信息
	 * @param tjButtonClick List
	 */
	void insertList(List<TjButtonClick> tjButtonClickList);
	
	/**
	 * 多条件查询按钮点击次数
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param buttonNameList 按钮名称
	 * @return
	 */
	List<SimpleMapDO> queryClickTimesListByParams(@Param("beginTime")String beginTime, @Param("endTime")String endTime, @Param("list")List<String> buttonNameList);
	
	/**
	 * 获取每天点击某按钮次数
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param buttonNameList 按钮名称
	 * @return
	 */
	List<SimpleMapDO> queryDayClickTimesByFarmeTime(@Param("beginTime")String beginTime, @Param("endTime")String endTime, @Param("list")List<String> buttonNameList);
}

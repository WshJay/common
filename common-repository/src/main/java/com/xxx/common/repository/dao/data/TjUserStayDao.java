package com.xxx.common.repository.dao.data ;

import com.xxx.common.repository.entity.data.TjUserStay;
import com.xxx.common.repository.base.SqlMapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TjUserStayDao extends SqlMapper {
	
	/**
	 * 根据ID查询表数据
	 * @param id
	 * @return
	 */
	public TjUserStay loadById(int id);
	
	/**
	 * 插入信息
	 * @param tjUserStay 
	 * @return
	 */
	int insert(TjUserStay tjUserStay);
	
	/**
	 * 根据ID删除信息
	 * @param id
	 */
	void deleteById(int id);
	
	/**
	 * 批量添加信息
	 * @param tjUserStay List
	 */
	void insertList(List<TjUserStay> tjUserStayList);
	
	Date selectMaxIntoTime(TjUserStay tjUserStay);
	
	/**
	 * 获取最大的插入时间
	 * @param deviceNo
	 * @param type
	 * @return
	 */
	TjUserStay loadMaxIntoTime(@Param("deviceNo") String deviceNo, @Param("type") int type);
	
	/**
	 * 更新设备停留时长
	 * @param id
	 * @param leaveTime
	 * @param stayLong
	 */
	void updateStayLongById(@Param("id") int id, @Param("leaveTime") Date leaveTime, @Param("stayLong") int stayLong);
	
}

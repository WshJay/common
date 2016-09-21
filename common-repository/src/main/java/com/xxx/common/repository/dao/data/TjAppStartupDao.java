package com.xxx.common.repository.dao.data ;

import com.xxx.common.repository.entity.base.SimpleMapDO;
import com.xxx.common.repository.entity.data.TjAppStartup;
import com.xxx.common.repository.base.SqlMapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TjAppStartupDao extends SqlMapper {
	
	/**
	 * 根据ID查询表数据
	 * @param id
	 * @return
	 */
	public TjAppStartup loadById(int id);
	
	/**
	 * 插入信息
	 * @param tjAppStartup 
	 * @return
	 */
	int insert(TjAppStartup tjAppStartup);
	
	/**
	 * 根据ID删除信息
	 * @param id
	 */
	void deleteById(int id);
	
	/**
	 * 批量添加信息
	 * @param tjAppStartup List
	 */
	void insertList(List<TjAppStartup> tjAppStartupList);
	
	/**
	 * 多条件查询APP启动表
	 * @param TjAppStartup
	 * @return
	 */
	List<TjAppStartup> queryListByParams(TjAppStartup tjAppStartup,
			RowBounds rowBounds);
	int queryCountByParams(TjAppStartup tjAppStartup);
	
	/**
	 * 根据时间范围查询用户启动数
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	List<SimpleMapDO> queryStartupTimesListByTimeFrame(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
	
	/**
	 * 根据时间范围查询用户启动天数
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	List<SimpleMapDO> queryStartupDaysByTimeFrame(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
	
	/**
	 * 根据时间范围查询每日活跃用户数量
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	List<SimpleMapDO> queryActiveUserNumByTimeFrame(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
	
	/**
	 * 根据时间范围查询每日用户总启动次数 
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	List<SimpleMapDO> queryAllStartupTimesByTimeFrame(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
	
	/**
	 * 根据设备号查询是否是新
	 */
	int selectByDeviceNo(@Param("deviceNo")String deviceNo);
	
	/**
	 * 查询每天新增设备号
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	List<SimpleMapDO> queryNewStartupByTimeFrame(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
}

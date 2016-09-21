package com.xxx.common.repository.dao.app.order ;

import com.xxx.common.repository.base.SqlMapper;
import com.xxx.common.repository.entity.app.order.AppPartyOrder;
import com.xxx.common.repository.entity.base.SimpleMapDO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppPartyOrderDao extends SqlMapper {
	
	/**
	 * 根据ID查询表数据
	 * @param id
	 * @return
	 */
	public AppPartyOrder loadById(int id);
	
	/**
	 * 插入信息
	 * @param appPartyOrder 
	 * @return
	 */
	int insert(AppPartyOrder appPartyOrder);
	
	/**
	 * 根据ID删除信息
	 * @param id
	 */
	void deleteById(int id);
	
	/**
	 * 批量添加信息
	 * @param appPartyOrder List
	 */
	void insertList(List<AppPartyOrder> appPartyOrderList);
	
	/**
	 * 根据订单状态查询用户购买数量
	 * @param statusList
	 * @return
	 */
	List<SimpleMapDO> queryBuyNumberByStatusList(@Param("list")List<Integer> statusList);
}

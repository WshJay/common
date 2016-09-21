package com.xxx.common.server.service.data;

import org.springframework.stereotype.Service;

/**
 * 数据服务层
 * Project:     <common-server>
 * File Name:   <DataService.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2016年4月17日 下午7:14:26
 */
@Service
public interface DataService {

	/**
	 * 生成用户全量表数据
	 * @param exportDate
	 * @param startupExcelPath
	 */
	public void generateAllUserData(String exportDate, String startupExcelPath);
	
	/**
	 * 统计活动某段时间内PV,UV(活动数据表)
	 * @param exportBeginDate
	 * @param exportEndDate
	 * @param startupExcelPath
	 */
//	public void generateAllPartyClickData(String exportBeginDate, String exportEndDate, String startupExcelPath);
}


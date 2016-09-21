package com.xxx.common.web.task;

import org.springframework.stereotype.Service;

/**
 * 数据平台定时任务
 * Project:     <common-web>
 * File Name:   <DataTaskService.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2016年4月17日 下午7:15:33
 */
@Service
public interface DataTaskService {
	
	/**
	 * 每天凌晨3点生成用户行为全量表
	 */
	public void generateAllUserDataTask();
}


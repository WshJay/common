package org.wsh.common.task.service;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 定时任务管理
 * Project:     <duoju-app>
 * File Name:   <QuartzManagerService.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <定时任务管理>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2016年3月8日 下午2:36:15
 */
@Service
public interface QuartzManagerService{

	/**
	 * 添加一个定时任务
	 * @param jobName 任务名
	 * @param jobGroupName 任务组名
	 * @param triggerName 触发器名
	 * @param triggerGroupName 触发器组名
	 * @param jobClass 任务
	 * @param date 任务时间
	 */
	public void addJob(String jobName, String jobGroupName,
					   String triggerName, String triggerGroupName, Class<? extends Job> jobClass, Date date);

	/**
	 * 根据触发器名称和触发器组名修改一个任务的触发时间
	 * @param triggerName 触发器名称
	 * @param triggerGroupName 触发器组名
	 * @param date 任务时间
	 */
	public void modifyJobTime(String triggerName, String triggerGroupName, Date date);

	/**
	 * 移除一个任务
	 * @param jobName 任务名
	 * @param jobGroupName 任务组名
	 * @param triggerName 触发器名
	 * @param triggerGroupName 触发器组名
	 */
	public void removeJob(String jobName, String jobGroupName,
						  String triggerName, String triggerGroupName);

//	/**
//	 * 添加一个定时任务
//	 * @param jobName 任务名
//	 * @param jobGroupName 任务组名
//	 * @param triggerName 触发器名
//	 * @param triggerGroupName 触发器组名
//	 * @param job 任务
//	 * @param time 时间设置，参考quartz说明文档
//	 * @param jobDataMap 定时任务参数
//	 */
//	public void addJob(String jobName, String jobGroupName,
//					   String triggerName, String triggerGroupName, Job job, String time, JobDataMap jobDataMap);
//
//	/**
//	 * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
//	 * @param jobName 任务名
//	 * @param time 时间设置，参考quartz说明文档
//	 */
//	public void modifyJobTime(String jobName, String time);
//
//	/**
//	 * 修改一个任务的触发时间
//	 * @param triggerName 触发器名称
//	 * @param triggerGroupName 触发器组名
//	 * @param time
//	 */
//	public void modifyJobTime(String triggerName,
//							  String triggerGroupName, String time);
//
//	/**
//	 * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
//	 * @param jobName 任务名
//	 */
//	public void removeJob(String jobName);
//
//	/**
//	 * 移除一个任务
//	 * @param jobName 任务名
//	 * @param jobGroupName 任务组名
//	 * @param triggerName 触发器名
//	 * @param triggerGroupName 触发器组名
//	 */
//	public void removeJob(String jobName, String jobGroupName,
//						  String triggerName, String triggerGroupName);

}


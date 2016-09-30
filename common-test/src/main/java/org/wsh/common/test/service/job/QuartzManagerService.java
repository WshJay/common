package org.wsh.common.test.service.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.springframework.stereotype.Service;

@Service
public interface QuartzManagerService{
	
	/**
	 * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	 * @param jobName 任务名
	 * @param job 任务
	 * @param time 时间设置，参考quartz说明文档
	 * @param jobDataMap 定时任务参数
	 */
	public void addJob(String jobName, Job job, String time, JobDataMap jobDataMap);
	
	/**
	 * 添加一个定时任务
	 * @param jobName 任务名
	 * @param jobGroupName 任务组名
	 * @param triggerName 触发器名
	 * @param triggerGroupName 触发器组名
	 * @param job 任务
	 * @param time 时间设置，参考quartz说明文档
	 * @param jobDataMap 定时任务参数
	 */
	public void addJob(String jobName, String jobGroupName,
			String triggerName, String triggerGroupName, Job job, String time, JobDataMap jobDataMap);
	
	/**
	 * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	 * @param jobName 任务名
	 * @param time 时间设置，参考quartz说明文档
	 */
	public void modifyJobTime(String jobName, String time);
	
	/**
	 * 修改一个任务的触发时间
	 * @param triggerName 触发器名称
	 * @param triggerGroupName 触发器组名
	 * @param time
	 */
	public void modifyJobTime(String triggerName,
			String triggerGroupName, String time);
	
	/**
	 * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
	 * @param jobName 任务名
	 */
	public void removeJob(String jobName);
	
	/**
	 * 移除一个任务
	 * @param jobName 任务名
	 * @param jobGroupName 任务组名
	 * @param triggerName 触发器名
	 * @param triggerGroupName 触发器组名
	 */
	public void removeJob(String jobName, String jobGroupName,
			String triggerName, String triggerGroupName);

}


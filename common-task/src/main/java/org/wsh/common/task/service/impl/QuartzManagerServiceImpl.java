package org.wsh.common.task.service.impl;

import java.util.Date;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.wsh.common.task.service.QuartzManagerService;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 定时任务管理类
 * Project:     <common-test>
 * File Name:   <QuartzManager.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <定时任务管理类>
 * JDK version used:      <JDK1.6> 
 * Quartz version used: <1.8.0>
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2016年3月7日 下午7:47:48
 */
@Service("quartzManagerService")
public class QuartzManagerServiceImpl implements QuartzManagerService {
	
	private static Logger log = LoggerFactory.getLogger(QuartzManagerServiceImpl.class);

	/** 默认任务组名 */
	private String JOB_GROUP_NAME = "defaultJobGroup";
	
	/** 默认触发器名 */
	private String TRIGGER_GROUP_NAME = "defaultTriggerGroup";
	
	/** 任务调度工厂 */
	@Autowired
	private SchedulerFactoryBean SCHEDULER_FACTORY;
	
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
					   String triggerName, String triggerGroupName, Class<? extends Job> jobClass, Date date){
		try {
			if (SCHEDULER_FACTORY != null) {
				log.info("Add Job:" + jobName + " Start...");
				Scheduler scheduler = SCHEDULER_FACTORY.getScheduler();
				// define the job and tie it to our MyJob class
				JobDetail jobDetail = newJob(jobClass)
						.withIdentity(jobName, jobGroupName)
						.build();

				Trigger trigger = newTrigger()
						.withIdentity(triggerName, triggerGroupName).startAt(date).build();

				// Tell quartz to schedule the job using our trigger
				scheduler.scheduleJob(jobDetail, trigger);
				log.info("Add Job:" + jobName + " End...");
			}else{
				log.error("Can not add job, Because SCHEDULER_FACTORY is null...");
			}
		} catch (SchedulerException e) {
			log.error("Add Job:" + jobName + " error:", e);
		}
	}

	/**
	 * 根据触发器名称和触发器组名修改一个任务的触发时间
	 * @param triggerName 触发器名称
	 * @param triggerGroupName 触发器组名
	 * @param date 任务时间
	 */
	public void modifyJobTime(String triggerName, String triggerGroupName, Date date){
		try {
			if (SCHEDULER_FACTORY != null) {
				log.info("Update triggerName:" + triggerName + " Start...");
				Scheduler scheduler = SCHEDULER_FACTORY.getScheduler();
				TriggerKey triggerKey = new TriggerKey(triggerName,triggerGroupName);
				Trigger trigger = newTrigger().withIdentity(triggerKey).startAt(date).build();
				scheduler.rescheduleJob(triggerKey, trigger);
				log.info("Update triggerName:" + triggerName + " End...");
			}else{
				log.error("Can not update job, Because SCHEDULER_FACTORY is null...");
			}
		} catch (SchedulerException e) {
			log.error("Update triggerName:" + triggerName + " error:", e);
		}
	}

	@Override
	public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
		try {
			if (SCHEDULER_FACTORY != null) {
				log.info("Remove Job:" + jobName + " Start...");
				Scheduler sched = SCHEDULER_FACTORY.getScheduler();
				/** 停止触发器 */
				sched.pauseTrigger(new TriggerKey(triggerName, triggerGroupName));
				/** 移除触发器 */
				sched.unscheduleJob(new TriggerKey(triggerName, triggerGroupName));
				/** 删除任务 */
				sched.deleteJob(new JobKey(jobName, jobGroupName));
				log.info("Remove Job:" + jobName + " End...");
			}else{
				log.error("Can not remove job, Because SCHEDULER_FACTORY is null...");
			}
		} catch (SchedulerException e) {
			log.error("Remove Job:" + jobName + " error:", e);
		}
	}
//
//	/**
//	 * 修改一个任务的触发时间
//	 * @param triggerName 触发器名称
//	 * @param triggerGroupName 触发器组名
//	 * @param time
//	 */
//	public void modifyJobTime(String triggerName,
//			String triggerGroupName, String time){
//		try {
//			if (SCHEDULER_FACTORY != null) {
//				log.info("Update JobTrigger:" + triggerName + " Start...");
//				Scheduler sched = SCHEDULER_FACTORY.getScheduler();
//				/** 创建任务实例(任务名，任务组，任务执行类) */
//				Trigger trigger = sched.getTrigger(triggerName, triggerGroupName);
//				if (trigger != null) {
//					CronTrigger ct = (CronTrigger) trigger;
//					/** 修改时间 */
//					ct.setCronExpression(time);
//					/** 重启触发器 */
//					sched.resumeTrigger(triggerName, TRIGGER_GROUP_NAME);
//				}
//				log.info("Update JobTrigger:" + triggerName + " End...");
//			}
//		} catch (SchedulerException e) {
//			log.error("Update JobTrigger:" + triggerName + " error:", e);
//		} catch (ParseException e) {
//			log.error("Update JobTrigger:" + triggerName + " error:", e);
//		}
//	}
//
//	/**
//	 * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
//	 * @param jobName 任务名
//	 */
//	public void removeJob(String jobName){
//		try {
//			if (SCHEDULER_FACTORY != null) {
//				log.info("Remove Job:" + jobName + " Start...");
//				Scheduler sched = SCHEDULER_FACTORY.getScheduler();
//				/** 停止触发器 */
//				sched.pauseTrigger(jobName, TRIGGER_GROUP_NAME);
//				/** 移除触发器 */
//				sched.unscheduleJob(jobName, TRIGGER_GROUP_NAME);
//				/** 删除任务 */
//				sched.deleteJob(jobName, JOB_GROUP_NAME);
//				log.info("Remove Job:" + jobName + " End...");
//			}
//		} catch (SchedulerException e) {
//			log.error("Remove Job:" + jobName + " error:", e);
//		}
//	}
//
//	/**
//	 * 移除一个任务
//	 * @param jobName 任务名
//	 * @param jobGroupName 任务组名
//	 * @param triggerName 触发器名
//	 * @param triggerGroupName 触发器组名
//	 */
//	public void removeJob(String jobName, String jobGroupName,
//			String triggerName, String triggerGroupName){
//		try {
//			if (SCHEDULER_FACTORY != null) {
//				log.info("Remove Job:" + jobName + " Start...");
//				Scheduler sched = SCHEDULER_FACTORY.getScheduler();
//				/** 停止触发器 */
//				sched.pauseTrigger(triggerName, triggerGroupName);
//				/** 移除触发器 */
//				sched.unscheduleJob(triggerName, triggerGroupName);
//				/** 删除任务 */
//				sched.deleteJob(jobName, jobGroupName);
//				log.info("Remove Job:" + jobName + " End...");
//			}
//		} catch (SchedulerException e) {
//			log.error("Remove Job:" + jobName + " error:", e);
//		}
//	}
}

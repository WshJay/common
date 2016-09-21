package com.xxx.common.test.service.job.impl;

import java.text.ParseException;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.xxx.common.test.service.job.QuartzManagerService;

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
public class QuartzManagerServiceImpl implements QuartzManagerService{
	
	private static Logger log = LoggerFactory.getLogger(QuartzManagerServiceImpl.class);

	/** 默认任务组名 */
	private String JOB_GROUP_NAME = "defaultJobGroup";
	
	/** 默认触发器名 */
	private String TRIGGER_GROUP_NAME = "defaultTriggerGroup";
	
	/** 任务调度工厂 */
	@Autowired
	private SchedulerFactoryBean SCHEDULER_FACTORY;
	
	/**
	 * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	 * @param jobName 任务名
	 * @param job 任务
	 * @param time 时间设置，参考quartz说明文档
	 * @param jobDataMap 定时任务参数
	 */
	public void addJob(String jobName, Job job, String time, JobDataMap jobDataMap){
		try {
			if (SCHEDULER_FACTORY != null) {
				log.info("Add Job:" + jobName + " Start...");
				Scheduler sched = SCHEDULER_FACTORY.getScheduler();
				/** 创建任务实例(任务名，任务组，任务执行类) */
				JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME,
						job.getClass());
				/** 加入定时任务参数 */
				if (jobDataMap != null) {
					jobDetail.setJobDataMap(jobDataMap);
				}
				/** 创建触发器(触发器名,触发器组) */
				CronTrigger trigger = new CronTrigger(jobName, TRIGGER_GROUP_NAME);
				/** 触发器时间设定 */
				trigger.setCronExpression(time);
				sched.scheduleJob(jobDetail, trigger);
				/** 启动 */
				if (!sched.isShutdown()){
					sched.start();
				}
				log.info("Add Job:" + jobName + " End...");
			}
		} catch (SchedulerException e) {
			log.error("Add Job:" + jobName + " error:", e);
		} catch (ParseException e) {
			log.error("Add Job:" + jobName + " error:", e);
		}
	}

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
			String triggerName, String triggerGroupName, Job job, String time, JobDataMap jobDataMap){
		try {
			if (SCHEDULER_FACTORY != null) {
				log.info("Add Job:" + jobName + " Start...");
				Scheduler sched = SCHEDULER_FACTORY.getScheduler();
				/** 创建任务实例(任务名，任务组，任务执行类) */
				JobDetail jobDetail = new JobDetail(jobName, jobGroupName,
						job.getClass());
				/** 加入定时任务参数 */
				if (jobDataMap != null) {
					jobDetail.setJobDataMap(jobDataMap);
				}
				/** 创建触发器(触发器名,触发器组) */
				CronTrigger trigger = new CronTrigger(triggerName, triggerGroupName);
				/** 触发器时间设定 */
				trigger.setCronExpression(time);
				sched.scheduleJob(jobDetail, trigger);
				/** 启动 */
				if (!sched.isShutdown()){
					sched.start();
				}
				log.info("Add Job:" + jobName + " End...");
			}
		} catch (SchedulerException e) {
			log.error("Add Job:" + jobName + " error:", e);
		} catch (ParseException e) {
			log.error("Add Job:" + jobName + " error:", e);
		}
	}

	/**
	 * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	 * @param jobName 任务名
	 * @param time 时间设置，参考quartz说明文档
	 */
	public void modifyJobTime(String jobName, String time){
		try {
			if (SCHEDULER_FACTORY != null) {
				log.info("Update Job:" + jobName + " Start...");
				Scheduler sched = SCHEDULER_FACTORY.getScheduler();
				/** 创建任务实例(任务名，任务组，任务执行类) */
				Trigger trigger = sched.getTrigger(jobName, TRIGGER_GROUP_NAME);
				if (trigger != null) {
					CronTrigger ct = (CronTrigger) trigger;
					/** 修改时间 */
					ct.setCronExpression(time);
					/** 重启触发器 */
					sched.resumeTrigger(jobName, TRIGGER_GROUP_NAME);
				}
				log.info("Update Job:" + jobName + " End...");
			}
		} catch (SchedulerException e) {
			log.error("Update Job:" + jobName + " error:", e);
		} catch (ParseException e) {
			log.error("Update Job:" + jobName + " error:", e);
		}
	}

	/**
	 * 修改一个任务的触发时间
	 * @param triggerName 触发器名称
	 * @param triggerGroupName 触发器组名
	 * @param time
	 */
	public void modifyJobTime(String triggerName,
			String triggerGroupName, String time){
		try {
			if (SCHEDULER_FACTORY != null) {
				log.info("Update JobTrigger:" + triggerName + " Start...");
				Scheduler sched = SCHEDULER_FACTORY.getScheduler();
				/** 创建任务实例(任务名，任务组，任务执行类) */
				Trigger trigger = sched.getTrigger(triggerName, triggerGroupName);
				if (trigger != null) {
					CronTrigger ct = (CronTrigger) trigger;
					/** 修改时间 */
					ct.setCronExpression(time);
					/** 重启触发器 */
					sched.resumeTrigger(triggerName, TRIGGER_GROUP_NAME);
				}
				log.info("Update JobTrigger:" + triggerName + " End...");
			}
		} catch (SchedulerException e) {
			log.error("Update JobTrigger:" + triggerName + " error:", e);
		} catch (ParseException e) {
			log.error("Update JobTrigger:" + triggerName + " error:", e);
		}
	}

	/**
	 * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
	 * @param jobName 任务名
	 */
	public void removeJob(String jobName){
		try {
			if (SCHEDULER_FACTORY != null) {
				log.info("Remove Job:" + jobName + " Start...");
				Scheduler sched = SCHEDULER_FACTORY.getScheduler();
				/** 停止触发器 */
				sched.pauseTrigger(jobName, TRIGGER_GROUP_NAME);
				/** 移除触发器 */
				sched.unscheduleJob(jobName, TRIGGER_GROUP_NAME);
				/** 删除任务 */
				sched.deleteJob(jobName, JOB_GROUP_NAME);
				log.info("Remove Job:" + jobName + " End...");
			}
		} catch (SchedulerException e) {
			log.error("Remove Job:" + jobName + " error:", e);
		}
	}

	/**
	 * 移除一个任务
	 * @param jobName 任务名
	 * @param jobGroupName 任务组名
	 * @param triggerName 触发器名
	 * @param triggerGroupName 触发器组名
	 */
	public void removeJob(String jobName, String jobGroupName,
			String triggerName, String triggerGroupName){
		try {
			if (SCHEDULER_FACTORY != null) {
				log.info("Remove Job:" + jobName + " Start...");
				Scheduler sched = SCHEDULER_FACTORY.getScheduler();
				/** 停止触发器 */
				sched.pauseTrigger(triggerName, triggerGroupName);
				/** 移除触发器 */
				sched.unscheduleJob(triggerName, triggerGroupName);
				/** 删除任务 */
				sched.deleteJob(jobName, jobGroupName);
				log.info("Remove Job:" + jobName + " End...");
			}
		} catch (SchedulerException e) {
			log.error("Remove Job:" + jobName + " error:", e);
		}
	}
}

package org.wsh.common.task.factory;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Service;

/**
 * 创建JobFactory,解决定时任务无法调用Spring bean问题
 * Project:     <common-test>
 * File Name:   <JobFactory.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <解决定时任务无法调用Spring bean问题>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2016年3月9日 上午11:36:21
 */
@Service("jobFactory")
public class JobFactory extends AdaptableJobFactory {

	/**
	 * 这个对象Spring会帮我们自动注入进来,也属于Spring技术范畴
	 */
	@Autowired
	private AutowireCapableBeanFactory capableBeanFactory;

	protected Object createJobInstance(TriggerFiredBundle bundle)
			throws Exception {
		/** 调用父类的方法 */
		Object jobInstance = super.createJobInstance(bundle);
		/** 进行注入,这属于Spring的技术,不清楚的可以查看Spring的API */
		capableBeanFactory.autowireBean(jobInstance);
		return jobInstance;
	}
}

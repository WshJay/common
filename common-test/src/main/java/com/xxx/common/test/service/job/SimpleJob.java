package com.xxx.common.test.service.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.common.repository.dao.UserBasicDAO;
import com.xxx.common.util.date.DateUtil;

@Service("simpleJob")
public class SimpleJob implements Job{
	
	private static Logger log = LoggerFactory.getLogger(SimpleJob.class); 
	
	@Autowired
	private UserBasicDAO userBasicDAO;
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		log.info("定时任务:" + DateUtil.parseDate(new Date(), DateUtil.YYYY_MM_DD_HH_MM_SS));
		userBasicDAO.queryALL();
	}
}


package org.wsh.common.test.job;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.test.service.job.QuartzManagerService;
import org.wsh.common.test.service.job.SimpleJob;
import org.wsh.common.dao.UserBasicDAO;
import org.wsh.common.util.date.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
//加载配置文件  
@ContextConfiguration(locations = { "classpath:/dal.xml", "classpath:/beans.xml"})
public class QuartzTest {
	
	private static Logger log = LoggerFactory.getLogger(QuartzTest.class);
	
	@Autowired
	private QuartzManagerService quartzManagerService;
	
	@Autowired
	private SimpleJob simpleJob;
	
	@Autowired
	private UserBasicDAO userBasicDAO;

	@Test
	public void test(){
		userBasicDAO.queryALL();
		Date currentDate = new Date();
		log.info(DateUtil.parseDate(currentDate, DateUtil.YYYY_MM_DD_HH_MM_SS));
//		for (int i = 0; i < 100; i++) {
			quartzManagerService.addJob("jobTest" + 1, simpleJob, DateUtil.formatDateForCron(DateUtil.calculateSecond(currentDate, 1)), null);
//		}
		/** 当前线程等待65秒  */
		try {
			Thread.sleep(100L * 1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


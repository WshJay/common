package org.wsh.common.test.user;

import java.util.Date;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wsh.common.util.date.DateUtil;


public class CreateUser {

	private static final Logger log = LoggerFactory.getLogger(DateUtil.class);
	
	@Test
	public void test(){
		for (int i = 0; i < 1500; i++) {
			Date randomDate= DateUtil.randomDate("2015-12-25 06:00:00","2015-12-25 23:59:59", DateUtil.YYYY_MM_DD_HH_MM_SS);
			log.info("日期：" + DateUtil.parseDate(randomDate, DateUtil.YYYY_MM_DD_HH_MM_SS));
		}
	}
	
}


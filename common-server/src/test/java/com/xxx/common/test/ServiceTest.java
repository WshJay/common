package com.xxx.common.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xxx.common.server.service.UserService;
import com.xxx.common.util.exception.BusinessException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/dal.xml", "classpath:/bean-scan.xml"})
public class ServiceTest {
	
	@Autowired
	private UserService userService;
	
	private final Logger log = LoggerFactory.getLogger(ServiceTest.class);

	@Test()
	public void test(){
		for (int i = 1; i < 2; i++) {
			try {
				userService.doAddUser(1l,"用户" + i, "123456", "用户" + i, "12356789328", "123@qq.com",1l);
			} catch (BusinessException e) {
				log.error(e.getMessage(), e);
			}
		}
	}
}


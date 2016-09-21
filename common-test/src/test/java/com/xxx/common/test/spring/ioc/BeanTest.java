package com.xxx.common.test.spring.ioc;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;

import com.xxx.common.test.service.TestService;
import com.xxx.common.test.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml"})
public class BeanTest {
	
	@Autowired
	private TestService testService;
	
	@Setter
	private String userName;
	
	@Test
	public void test(){
		getBean();
	}
	
	void getBean(){
		System.out.println(userName);
		System.out.println(testService);
	}

}


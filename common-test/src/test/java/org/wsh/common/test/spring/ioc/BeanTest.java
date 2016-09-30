package org.wsh.common.test.spring.ioc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;

import org.wsh.common.test.service.TestService;

import static org.wsh.common.enums.SessionKey.userName;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml"})
public class BeanTest {
	
	@Autowired
	private TestService testService;

	@Test
	public void test(){
		testService.test();
		System.out.println(userName);
	}
}


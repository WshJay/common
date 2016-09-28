package com.xxx.common.test.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml"})
public class TestException1{
	
	private final Logger log = LoggerFactory.getLogger(TestException1.class);

	@Test
	public void test() {
		
	}

}


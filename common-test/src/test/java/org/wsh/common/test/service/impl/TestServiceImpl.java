package org.wsh.common.test.service.impl;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.wsh.common.test.service.TestService;

public class TestServiceImpl implements TestService {
	
	private final Logger log = LoggerFactory.getLogger(TestServiceImpl.class);

	@Setter
	private String userName;

	@Override
	public void test() {
		log.info("userName==>" + userName);
		log.info("SingletonTest...");
	}

}


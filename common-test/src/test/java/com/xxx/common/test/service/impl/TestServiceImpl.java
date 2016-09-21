package com.xxx.common.test.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xxx.common.test.service.TestService;

public class TestServiceImpl implements TestService{
	
	private final Logger log = LoggerFactory.getLogger(TestServiceImpl.class);

	@Override
	public void Test() {
		log.info("SingletonTest...");
	}

}


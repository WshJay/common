package com.xxx.common.test.spring.ioc;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

public class FileSystemXmlApplicationContextTest extends
		FileSystemXmlApplicationContext {
	
	private final Logger log = LoggerFactory.getLogger(FileSystemXmlApplicationContextTest.class);
	
	@Test
	public void test() {
		
		String location = "beans.xml";
		setConfigLocation(location);
		Resource resource = getResourceByPath(location);
		log.info("fileName:" + resource.getFilename());
	}

}


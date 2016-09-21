package com.xxx.common.test.pattern.lsth;

import java.util.HashMap;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.xxx.common.test.pattern.lsth.Son;

public class SonTest extends Son {
	
	private final Logger log = Logger.getLogger(SonTest.class);

	@SuppressWarnings("rawtypes")
	@Test
	public void test() {
		HashMap map = new HashMap();
		doSomething(map);
	}

}


package com.xxx.common.test.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xxx.common.test.order.base.IProcessor;
import com.xxx.common.test.order.base.OrderIProcessor;
import com.xxx.common.test.order.enums.PartyType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml"})
public class OrderTest {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void test(){
		IProcessor iProcessor = OrderIProcessor.getProcessor(PartyType.OFFICIAL_PARTY.KEY);
		iProcessor.addOrder();
		IProcessor iProcessor1 = OrderIProcessor.getProcessor(PartyType.USER_PARTY.KEY);
		iProcessor1.addOrder();
	}
}


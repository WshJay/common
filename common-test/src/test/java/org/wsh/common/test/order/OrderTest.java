package org.wsh.common.test.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.test.order.duoju.base.IProcessor;
import org.wsh.common.test.order.duoju.base.OrderIProcessor;
import org.wsh.common.test.order.duoju.enums.PartyType;
import org.wsh.common.test.order.duoju.service.OfficialPartyOrder;
import org.wsh.common.test.order.duoju.service.UserPartyOrder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml"})
public class OrderTest {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void test(){

		new OfficialPartyOrder();
		new UserPartyOrder();

		IProcessor iProcessor = OrderIProcessor.getProcessor(PartyType.OFFICIAL_PARTY.KEY);
		iProcessor.addOrder();
		IProcessor iProcessor1 = OrderIProcessor.getProcessor(PartyType.USER_PARTY.KEY);
		iProcessor1.addOrder();
	}
}


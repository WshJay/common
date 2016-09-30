package org.wsh.common.test.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.wsh.common.test.order.base.OrderIProcessor;
import org.wsh.common.test.order.enums.PartyType;
import org.wsh.common.support.response.ResponseDO;

public class OfficialPartyOrder extends OrderIProcessor{
	
	private static final int APP_PARTY_TYPE_ID = PartyType.OFFICIAL_PARTY.KEY;
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public ResponseDO addOrder() {
		log.info("官方活动下订单...");
		return null;
	}

	@Override
	public Integer getAppPartyTypeId() {
		return APP_PARTY_TYPE_ID;
	}
}


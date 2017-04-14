package org.wsh.common.test.order.duoju.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.wsh.common.test.order.duoju.base.OrderIProcessor;
import org.wsh.common.test.order.duoju.enums.PartyType;
import org.wsh.common.support.response.ResponseDO;

public class UserPartyOrder extends OrderIProcessor{

	private final static int APP_PARTY_TYPE_ID = PartyType.USER_PARTY.KEY;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public ResponseDO addOrder() {
		log.info("用户活动下订单...");
		return null;
	}

	@Override
	public Integer getAppPartyTypeId() {
		return APP_PARTY_TYPE_ID;
	}

}


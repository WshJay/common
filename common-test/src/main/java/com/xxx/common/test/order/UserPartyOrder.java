package com.xxx.common.test.order;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xxx.common.repository.data.support.ResponseDO;
import com.xxx.common.test.order.base.OrderIProcessor;
import com.xxx.common.test.order.enums.PartyType;

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


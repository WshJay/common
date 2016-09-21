package com.xxx.common.test.order;

import java.util.Map;

import lombok.core.Main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xxx.common.repository.data.support.ResponseDO;
import com.xxx.common.test.order.base.OrderIProcessor;
import com.xxx.common.test.order.enums.PartyType;

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


package org.wsh.common.test.order.duoju.base;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class OrderIProcessor implements IProcessor{

	private static final Map<Integer, IProcessor> PROCESSOR_MAP = new HashMap<Integer, IProcessor>();

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public OrderIProcessor() {
		PROCESSOR_MAP.put(getAppPartyTypeId(), this);
	}

	public static IProcessor getProcessor(int appPartyTypeId) {
		return PROCESSOR_MAP.get(appPartyTypeId);
	}
}


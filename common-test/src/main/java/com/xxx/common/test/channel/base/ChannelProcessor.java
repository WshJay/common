package com.xxx.common.test.channel.base;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ChannelProcessor implements IProcessor{

	private static final Map<Integer, IProcessor> PROCESSOR_MAP = new HashMap<Integer, IProcessor>();

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public ChannelProcessor() {
		PROCESSOR_MAP.put(getChannelId(), this);
	}

	public static IProcessor getProcessor(int channelId) {
		return PROCESSOR_MAP.get(channelId);
	}
}


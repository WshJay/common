package com.xxx.common.test.channel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xxx.common.test.channel.base.ChannelProcessor;
import com.xxx.common.test.channel.base.IProcessor;
import com.xxx.common.test.channel.bean.ChannelOrderDO;

public class Test {

	public static void main(String[] args) {
		int channelId = 1;
		Map map = new HashMap();
		map.put("key", "test");
		IProcessor iProcessor = ChannelProcessor.getProcessor(channelId);
		final List<ChannelOrderDO> channelOrderDOList = iProcessor.getChannelOrderList(channelId, map);
	}
}


package org.wsh.common.test.channel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.wsh.common.test.channel.base.ChannelProcessor;
import org.wsh.common.test.channel.base.IProcessor;
import org.wsh.common.test.channel.bean.ChannelOrderDO;

public class Test {

	public static void main(String[] args) {
		int channelId = 1;
		Map map = new HashMap();
		map.put("key", "test");
		IProcessor iProcessor = ChannelProcessor.getProcessor(channelId);
		final List<ChannelOrderDO> channelOrderDOList = iProcessor.getChannelOrderList(channelId, map);
	}
}


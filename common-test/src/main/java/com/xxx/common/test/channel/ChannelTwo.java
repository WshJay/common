package com.xxx.common.test.channel;

import java.util.List;
import java.util.Map;

import com.xxx.common.test.channel.base.ChannelProcessor;
import com.xxx.common.test.channel.bean.ChannelOrderDO;

public class ChannelTwo extends ChannelProcessor{
	
	final int channelId = 2;

	@Override
	public List<ChannelOrderDO> getChannelOrderList(int channelId, Map params) {
		// TODO 具体实现
		System.out.println("执行通道：" + channelId);
		return null;
	}

	@Override
	public int getChannelId() {
		return channelId;
	}

}


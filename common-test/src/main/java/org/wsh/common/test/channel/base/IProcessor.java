package org.wsh.common.test.channel.base;

import java.util.List;
import java.util.Map;

import org.wsh.common.test.channel.bean.ChannelOrderDO;

public interface IProcessor {

	/**
	 * 获取通道数据
	 * @param channelId
	 * @param channelData
	 * @return
	 */
	public List<ChannelOrderDO> getChannelOrderList(int channelId, Map params);
	
	/**
	 * 获取通道ID
	 * @return
	 */
	public int getChannelId();
}


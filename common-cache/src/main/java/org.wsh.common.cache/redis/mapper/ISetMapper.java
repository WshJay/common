package org.wsh.common.cache.redis.mapper;

import redis.clients.jedis.ShardedJedis;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  集合元素
 * since Date： 2016/11/24 13:54
 */
public interface ISetMapper extends IMapper {

	/**
	 * 集合元素mapper函数
	 * 
	 * @param value
	 *            每一项的实际值
	 */
	public void mapper(ShardedJedis jedis, String value);

	/**
	 * 集合元素项转成字符串类型
	 */
	public String value();
}

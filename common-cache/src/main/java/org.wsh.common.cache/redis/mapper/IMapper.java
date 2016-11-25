package org.wsh.common.cache.redis.mapper;

import redis.clients.jedis.ShardedJedis;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  redis - object之间的映射
 * since Date： 2016/11/24 13:53
 */
public interface IMapper {

	/**
	 * 缓存项的key
	 */
	public String key();

	/**
	 * 保存
	 */
	public void save(ShardedJedis jedis);
}

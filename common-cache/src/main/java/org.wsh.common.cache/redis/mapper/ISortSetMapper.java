package org.wsh.common.cache.redis.mapper;

import redis.clients.jedis.ShardedJedis;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  排序集合
 * since Date： 2016/11/24 13:54
 */
public interface ISortSetMapper extends ISetMapper {

	/**
	 * 	集合元素mapper函数
	 * 	@param	value		每一项的实际值
	 * 	@param	score		每一项的排序值
	 */
	public void mapper(ShardedJedis jedis, String value, Double score);
	/**
	 * 
	 */
	public Double score();
}

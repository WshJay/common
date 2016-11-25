package org.wsh.common.cache.redis.mapper;

import java.util.Map;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  hash数据结构 - 对象间的映射
 * since Date： 2016/11/24 13:54
 */
public interface IHashMapper extends IMapper {

	/**
	 * 
	 */
	public void mapper(Map<String, String> kv);

}

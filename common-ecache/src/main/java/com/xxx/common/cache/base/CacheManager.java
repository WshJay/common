package com.xxx.common.cache.base;

/**
 * 缓存管理接口
 * File Name: <CacheManager.java>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-4-15 下午4:16:49
 */
public interface CacheManager {
	
	/**
	 * 根据缓存名称获取缓存
	 * @param name
	 * @return
	 */
	public <K, V> Cache<K, V> getCache(String name);

}


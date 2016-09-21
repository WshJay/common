package com.xxx.common.cache.base;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.CacheException;

/**
 * 缓存接口
 * File Name: <Cache.java>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-4-15 下午4:16:33
 */
public interface Cache<K, V> {

   /**
    * 根据Key获取缓存内容
    * @param key
    * @return
    */
    public V get(K key)throws CacheException;

    /**
     * 将内容放入缓存
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value)throws CacheException;

   /**
    * 根据Key移除缓存
    * @param key
    * @return
    */
    public V remove(K key)throws CacheException;

    /**
     * 清除所有缓存(慎用)
     */
    public void clear()throws CacheException;

   /**
    * 获取缓存个数
    * @return
    */
    public int size();

    /**
     * 获取所有Key值
     * @return
     */
    public Set<K> keys();

    /**
     * 获取所有缓存内容
     * @return
     */
    public Collection<V> values();
	
}


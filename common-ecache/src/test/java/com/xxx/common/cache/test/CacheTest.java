package com.xxx.common.cache.test;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheTest {
	
	private static Logger log = LoggerFactory.getLogger(CacheTest.class);
	
	@Test
	public void cacheTest(){
		log.info("test");
		// 使用默认配置文件创建CacheManager
		CacheManager manager = CacheManager.create();
		// 通过manager可以生成指定名称的Cache对象
		Cache cache = manager.getCache("shiro-kickout-session");
		//往cache中添加元素
		Element element = new Element("userName", "xiaohui");
		cache.put(element);
		//从cache中取回元素
		Element element1 = cache.get("userName");
		log.info(element1.getObjectValue().toString());
		// 使用manager移除指定名称的Cache对象
//		manager.removeCache("demoCache");
		
	}
}


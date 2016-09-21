package com.xxx.common.test.pattern.lsth;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * 子类
 * File Name: <Son.java>
 * Comments:  <里氏替换原则>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-4-28 上午11:39:30
 */
public class Son extends Father{

	@SuppressWarnings("rawtypes")
	public Collection doSomething(Map map) {
		System.out.println("子类被执行");
		return map.values();
	}
	
	/*@SuppressWarnings("rawtypes")
	@Override
	public Collection doSomething(HashMap map) {
		System.out.println("子类被执行...");
		return map.values();
	}*/
}


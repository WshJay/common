package com.xxx.common.test.pattern.lsth;

import java.util.Collection;
import java.util.HashMap;

/**
 * 父类
 * File Name: <Father.java>
 * Comments:  <里氏替换原则>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-4-28 上午11:38:39
 */
public class Father {

	@SuppressWarnings("rawtypes")
	public Collection doSomething(HashMap map) {
		System.out.println("父类被执行");
		return map.values();
	}
}


package com.xxx.common.test.pattern.dip.three;

import com.xxx.common.test.pattern.dip.base.ICar;

/**
 * 接口声明依赖对象
 * File Name: <Driver.java>
 * Comments:  <依赖倒置原则DependenceInversionPrinciple>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-4-28 下午2:26:47
 */
public class Driver implements IDriver{

	@Override
	public void driver(ICar car) {
		car.run();
	}

}


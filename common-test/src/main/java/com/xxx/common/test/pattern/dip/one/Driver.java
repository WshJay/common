package com.xxx.common.test.pattern.dip.one;

import com.xxx.common.test.pattern.dip.base.ICar;

/**
 * 构造函数传递依赖对象
 * File Name: <Driver.java>
 * Comments:  <依赖倒置原则DependenceInversionPrinciple>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-4-28 下午1:48:50
 */
public class Driver implements IDriver{

	private ICar car;

	public Driver(ICar car) {
		this.car = car;
	}
	
	public void drive() {
		car.run();
	}

	public void setCar(ICar car) {
		car.run();
	}

}


package com.xxx.common.test.pattern.dip.two;

import com.xxx.common.test.pattern.dip.base.ICar;

/**
 * Setter方法传递依赖对象
 * File Name: <Driver.java>
 * Comments:  <依赖倒置原则DependenceInversionPrinciple>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-4-28 下午2:18:53
 */
public class Driver implements IDriver{

	private ICar car;
	
	@Override
	public void setCar(ICar car) {
		this.car = car;
	}

	@Override
	public void drive() {
		car.run();
	}

}


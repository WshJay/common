package org.wsh.common.test.pattern.dip.three;

import org.wsh.common.test.pattern.dip.base.ICar;

/**
 * 接口声明依赖对象
 * File Name: <IDriver.java>
 * Comments:  <依赖倒置原则DependenceInversionPrinciple>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-4-28 下午2:27:05
 */
public interface IDriver {
	
	public void driver(ICar car);
	
}


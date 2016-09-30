package org.wsh.common.test.pattern.dip;

import org.wsh.common.test.pattern.dip.base.ICar;


public class BMW implements ICar {

	@Override
	public void run() {
		System.out.println("宝马汽车开始运行...");
	}
}


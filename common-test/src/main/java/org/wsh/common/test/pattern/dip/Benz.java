package org.wsh.common.test.pattern.dip;

import org.wsh.common.test.pattern.dip.base.ICar;


public class Benz implements ICar {

	@Override
	public void run() {
		System.out.println("奔驰汽车开始运行...");
	}
}


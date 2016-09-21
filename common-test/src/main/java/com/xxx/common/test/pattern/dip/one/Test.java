package com.xxx.common.test.pattern.dip.one;

import com.xxx.common.test.pattern.dip.BMW;
import com.xxx.common.test.pattern.dip.Benz;

public class Test {

	public static void main(String[] args) {
		Driver driver = new Driver(new BMW());
		driver.setCar(new Benz());
		driver.drive();
	}
}


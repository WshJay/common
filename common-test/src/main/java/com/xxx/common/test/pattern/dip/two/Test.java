package com.xxx.common.test.pattern.dip.two;

import com.xxx.common.test.pattern.dip.BMW;
import com.xxx.common.test.pattern.dip.one.Driver;

public class Test {

	public static void main(String[] args) {
		Driver driver = new Driver(new BMW());
		driver.drive();
	}
}


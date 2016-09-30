package org.wsh.common.test.pattern.dip.two;

import org.wsh.common.test.pattern.dip.BMW;
import org.wsh.common.test.pattern.dip.one.Driver;

public class Test {

	public static void main(String[] args) {
		Driver driver = new Driver(new BMW());
		driver.drive();
	}
}


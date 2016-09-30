package org.wsh.common.test.pattern.dip.one;

import org.wsh.common.test.pattern.dip.BMW;
import org.wsh.common.test.pattern.dip.Benz;

public class Test {

	public static void main(String[] args) {
		Driver driver = new Driver(new BMW());
		driver.setCar(new Benz());
		driver.drive();
	}
}


package com.xxx.common.test.pattern.dip.one;

import static org.junit.Assert.*;

import org.junit.Test;

import com.xxx.common.test.pattern.dip.base.ICar;

public class DriverTest extends Driver {

	public DriverTest(ICar car) {
		super(car);
	}

	@Test
	public void test() {
//		Driver d = new Driver(new Beanz());
	}

}


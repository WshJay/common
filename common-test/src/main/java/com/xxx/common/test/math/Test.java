package com.xxx.common.test.math;

/**
 * Math.log
 * Project:     <common-test>
 * File Name:   <Test.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2015-7-29 下午6:39:06
 */
public class Test {

	public static void main(String[] args) {
		demo();
		double k01 = 100;
		double k02 = 75;
//		System.out.println(Math.log(1000) - Math.log(10));
	}

	/**
	 * 算法检测例子
	 */
	public static void demo() {

		// 第一层的失活常数A1=(-1/t01)*(ln(K1/k01))
		// 第二层的失活常数A2=(-1/t02)*(ln(K2/k02))
		// 第三层的失活常数 A3=((1/k03)*(A2K2) ²)/(A1k1)
		double k01 = 10;
		double k02 = 75;
		double k03 = 80;
		double t01 = 24;
		double k1 = 1000;
		double t02 = 48;
		double k2 = 56;

		double A1 = (-1 / t01) * (Math.log(k1) - Math.log(k01));
		double A2 = (-1 / t02) * (Math.log(k2) - Math.log(k02));
		double A3 = (1 / k03) * (Math.pow((A2 * k2), 2)) / (A1 * k1);
		System.out.println("A1=" + A1 + " A2=" + A2 + " A3=" + A3);

		// double K1 = k01* Math.exp(-A1*t1);
		// double K2 = k02* Math.exp(-A2*t2);
		// double K3 = k03* Math.exp(-A3*t3);
		double t1 = 24;
		double t2 = 48;
		double t3 = 72;
		double K1 = k01 * Math.exp(-A1 * t1);
		double K2 = k02 * Math.exp(-A2 * t2);
		double K3 = k03 * Math.exp(-A3 * t3);
		System.out.println("K1=" + K1 + " K2=" + K2 + " K3=" + K3);
	}

}

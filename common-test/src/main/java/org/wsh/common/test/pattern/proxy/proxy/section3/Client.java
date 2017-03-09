package org.wsh.common.test.pattern.proxy.proxy.section3;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 */
public class Client {
	
	public static void main(String[] args) {
		Subject proxy = new RealSubject();
		proxy.request();
	}
}

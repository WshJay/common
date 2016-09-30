package org.wsh.common.provider.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Provider {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/bean.xml","classpath:/application-provider.xml");
		context.start();

		System.in.read(); // 按任意键退出
	}
}

package org.wsh.common.provider.test;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderService {

	private ClassPathXmlApplicationContext context = null;

	public void start(String configLocation) {
		synchronized (this) {
			if (context == null) {
				context = new ClassPathXmlApplicationContext("classpath:" + configLocation);
				context.start();
			}
		}
	}

	public static void main(String[] args) {
		try {
			String configLocation = null;
			if (args != null && args.length > 0) {
				configLocation = args[0];
			} else if (StringUtils.isBlank(configLocation)) {
				configLocation = "provider-bean.xml";
			}
			new ProviderService().start(configLocation);
			System.out.println("++++++[dubbo-common服务已启动完成]++++++" + "You can view the log details");
			while (true) {
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

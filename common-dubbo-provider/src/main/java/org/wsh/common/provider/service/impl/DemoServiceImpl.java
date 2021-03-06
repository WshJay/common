package org.wsh.common.provider.service.impl;

import org.springframework.stereotype.Service;
import org.wsh.common.consumer.service.DemoService;
import org.wsh.common.util.logger.LoggerService;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * DemoService实现类
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-5-23 上午10:57:39
 */
@Service("demoService")
@com.alibaba.dubbo.config.annotation.Service(version = "1.0.0")
public class DemoServiceImpl extends LoggerService implements DemoService {

	private int count;

	public String sayHello() {
		count++;
		String str = "common:Hello dubbo" + count;
		System.out.println("common服务方：" + str);
		logger.info("common服务方：" + str);
		return str;
	}
}

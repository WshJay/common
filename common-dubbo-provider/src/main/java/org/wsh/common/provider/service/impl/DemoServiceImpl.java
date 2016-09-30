package org.wsh.common.provider.service.impl;

import org.wsh.common.consumer.service.DemoService;
import org.springframework.stereotype.Service;
import org.wsh.common.provider.service.base.BaseService;

/**
 * DemoService实现类
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-5-23 上午10:57:39
 */
@Service("demoService")
public class DemoServiceImpl extends BaseService implements DemoService {

	private int count;

	public String sayHello() {
		count++;
		String str = "common:Hello dubbo" + count;
		System.out.println("common服务方：" + str);
		log.info("common服务方：" + str);
		return str;
	}
}

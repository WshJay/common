package org.wsh.dubbo.service.impl;

import com.xxx.consumer.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static javafx.scene.input.KeyCode.L;

/**
 * DemoService实现类
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-5-23 上午10:57:39
 */
public class DemoServiceImpl implements DemoService {

	Logger log = LoggerFactory.getLogger(DemoServiceImpl.class);

	
	private int count;

	public String sayHello() {
		count++;
		String str = "common:Hello dubbo" + count;
		System.out.println("common服务方：" + str);
		log.info("common服务方：" + str);
		return str;
	}
}

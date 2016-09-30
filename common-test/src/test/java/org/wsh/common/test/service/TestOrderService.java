package org.wsh.common.test.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.wsh.common.test.service.order.OrderService;
import org.wsh.common.test.service.order.impl.UserOrderServiceImpl;

/**
 * 订单接口测试
 * Project:     <common-test>
 * File Name:   <TestOrderService.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <订单接口测试>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2015年11月19日 下午10:22:37
 */
public class TestOrderService {
	
	private final Logger log = LoggerFactory.getLogger(TestOrderService.class);

	@Test
	public void test(){
		UserOrderServiceImpl userOrderServiceImpl = new UserOrderServiceImpl();
		getOrderService(userOrderServiceImpl);
	}
	
	public void getOrderService(OrderService orderService){
		orderService.createOrder();
	}
}


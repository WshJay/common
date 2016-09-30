package org.wsh.common.test.service.order.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.wsh.common.test.service.order.OrderService;
import org.wsh.common.support.response.ResponseDO;

/**
 * 用户订单接口
 * Project:     <common-test>
 * File Name:   <UserOrderServiceImpl.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <用户订单接口>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2015年11月19日 下午10:12:12
 */
public class UserOrderServiceImpl implements OrderService {
	
	private final Logger log = LoggerFactory.getLogger(UserOrderServiceImpl.class);

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDO createOrder() {
		log.info("用户下单...");
		return null;
	}

}


package org.wsh.common.test.service.order.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.wsh.common.test.service.order.OrderService;
import org.wsh.common.support.response.ResponseDO;

/**
 * 商家下单
 * Project:     <common-test>
 * File Name:   <BussinessOrderServiceImpl.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <商家下单>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2015年11月19日 下午10:21:30
 */
public class BussinessOrderServiceImpl implements OrderService {
	
	private final Logger log = LoggerFactory.getLogger(BussinessOrderServiceImpl.class);

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDO createOrder() {
		log.info("商家下单...");
		return null;
	}

}


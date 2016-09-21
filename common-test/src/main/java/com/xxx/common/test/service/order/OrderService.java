package com.xxx.common.test.service.order;

import com.xxx.common.repository.data.support.ResponseDO;

/**
 * 订单接口
 * Project:     <common-test>
 * File Name:   <OrderService.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <订单接口>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2015年11月19日 下午10:10:53
 */
public interface OrderService {

	@SuppressWarnings("rawtypes")
	public ResponseDO createOrder();
}


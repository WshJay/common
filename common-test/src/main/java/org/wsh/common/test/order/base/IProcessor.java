package org.wsh.common.test.order.base;

import org.wsh.common.support.response.ResponseDO;

/**
 * 订单接口
 * Project:     <common-test>
 * File Name:   <IProcessor.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2015年12月7日 下午10:51:30
 */
public interface IProcessor {

	/**
	 * 下订单
	 * @param appPartyTypeId 活动类型ID
	 * @return
	 */
	public ResponseDO addOrder();
	
	/**
	 * 
	 * @return
	 */
	public Integer getAppPartyTypeId();
	
}


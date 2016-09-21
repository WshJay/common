package com.xxx.common.test.order.enums;

/**
 * 活动类型枚举类
 * Project:     <common-test>
 * File Name:   <PartyType.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <活动类型枚举类>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2015年12月7日 下午11:31:48
 */
public enum PartyType {

	OFFICIAL_PARTY(1,"官方活动"),
	BUSINESS_PARTY(2,"官方认证"),
	USER_PARTY(3,"用户活动"),
	THIRD_PARTY(4,"第三方活动"),
	RECOMMEND_PARTY(5,"官方推荐");
	
	// 成员变量
    public int KEY;  
    public String VALUE;
    
    // 构造方法
    private PartyType(Integer KEY,String VALUE) {
        this.KEY = KEY;
        this.VALUE = VALUE;
    }
    
    // 覆盖方法
    @Override
    public String toString() {
        return this.KEY + "_" + this.VALUE;
    }
}


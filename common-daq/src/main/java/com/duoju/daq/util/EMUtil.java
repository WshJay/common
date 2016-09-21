package com.duoju.daq.util;

import lombok.Getter;
import lombok.Setter;

/** 
 * 创建人: LCN
 * 创建时间：2015年10月16日
 * 类说明 :枚举类
 */
public class EMUtil {
	
	public static String DefaultAvatar="http://image.duoju.info/appImg/hunter.jpg";//用户默认头像
	public static String DefaultBgURL="http://image.duoju.info/app/default_bg.png";
	
	/**
	 * 活动类型
	 * @author wsh
	 */
	public enum PARTY_TYPE {
		
		// 1:官方活动 2:官方认证  3:用户活动 4:第三方活动  5:官方推荐
		OFFICIAL_PARTY(1,"官方活动"),
		BUSINESS_PARTY(2,"官方认证"),
		USER_PARTY(3,"用户活动"),
		THIRD_PARTY(4,"第三方活动"),
		RECOMMEND_PARTY(5,"官方推荐");
		
		// 成员变量
        public int KEY;  
        public String VALUE;
        
        // 构造方法
        private PARTY_TYPE(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }
        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
    }
	
    /** 
     * 活动类型
     * @author ZhuZF
     * 
     * */
	public enum PARTY_SUB_TYPE{
		GENERAL_PARTY(1,"普通活动"),
		RECOMMENT_PARTY(2,"推荐活动"),
		GROUP_PARTY(3,"圈内活动"),
		CHOSEN_PARTY(4,"精选活动");
		 // 成员变量
        public Integer KEY;  
        public String VALUE;
        // 构造方法
        private PARTY_SUB_TYPE(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }

        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
	}
	/**
	*报名表状态
	*@author ZhuZF
	*/
	public enum PartyRegisterStatusEnums {
		Wait_Check(1,"等待审核"),
		Through(2,"通过"),
		UnThrough(3,"拒绝"),
		Cancel(4,"取消"),
		No_Register(5,"未报名");
		private int id;
		private String name;
		private PartyRegisterStatusEnums(int id,String name){
			this.id=id;
			this.name=name;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	/**
	 * 返回码
	 * @author Administrator
	 *
	 */
	public enum RESPONS_STATUS {
		SUCCESS_CODE(1,"请求成功"),
		ERROR_CODE(-1,"请求失败");
		
		 // 成员变量
        public Integer KEY;  
        public String VALUE;
        // 构造方法
        private RESPONS_STATUS(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }

        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
	}
	/**
	 * 短信类型枚举
	 */
	public enum MessageCodeEnums {
		MessageCodeRegister(1,"短信注册"),MessageCodeBindPhone(3,"绑定手机"),MessageCodeFindPassword(4,"找回密码");
		private int id;
		private String name;
		
		private MessageCodeEnums(int id,String name){
			this.id=id;
			this.name=name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		
	}
	
	
	/**
	 * 订单状态
	 * @author wsh
	 * 1 未审核  2：审核通过  3:审核不通过  4:删除订单 5 取消订单 6:成功 7:异常失败 8:待付款 9 交易关闭 10 退款中 11已退款 12已过期 13 已使用
	 */
	public enum ORDER_STATUS{
		CHECK_WAIT(1,"待审核"),
		CHECK_SUCCESS(2,"审核通过"),
		CHECK_FAILED(3,"审核不通过"),
		DELETE_ORDER(4,"删除订单"),
		CANCEL_ORDER(5,"已取消"),
		SUCCESS(6,"成功"),
		EXCEPTION_ERROR(7,"异常失败"),
		WAIT_PAY(8,"待付款"),
		TRADE_CLOSE(9,"交易关闭"),
		REFUNDING(10,"退款中"),
		REFUNDED(11,"已退款"),
		OVERDUE(12,"已过期"),
		USED(13,"已使用");
		
		// 成员变量
        public Integer KEY;  
        public String VALUE;
        // 构造方法
        private ORDER_STATUS(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }
        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
	}
	
	/**
	 * 订单状态描述（用于界面显示）
	 * @author Djq
	 */
	public enum ORDER_ROW_STATUS_ENUMS {

		ORDER_CLOSE(8, "交易关闭"),
		ORDER_LOCK(7,"订单锁定,待付款"),
		REFUND_OVER(6, "退款成功"), 
		REFUND_WAIT(5, "退款中"), 
		EVALUATION_OVER(4, "已评价"), 
		EVALUATION_WAIT(3, "待评价"), 
		REGISTER(2, "已报名"),
		CHECK_WAIT(1,"待审核");
		
		// 成员变量
        public Integer KEY;  
        public String VALUE;
        // 构造方法
        private ORDER_ROW_STATUS_ENUMS(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }
        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
	}
	
	/**
	 * 支付方式
	 * @author wsh
	 */
	public enum ORDER_PAY_WAY {

		ALIPAY(1, "支付宝支付"),
		WEIXINPAY(2,"微信支付"),
		NOPAY(0,"未支付");
		
		// 成员变量
        public Integer KEY;  
        public String VALUE;
        // 构造方法
        private ORDER_PAY_WAY(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }
        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
    }
	
	/**
	 * 票支付方式
	 * @author wsh
	 */
	public enum TICKET_PAY_METHOD {
		// 1:积分抵扣   2：现金支付 3：线下支付
		SCOREPAY(1, "积分抵扣"),
		MONEYPAY(2,"现金支付"),
		OFFOLINEPAY(3,"线下支付");
		
		// 成员变量
        public Integer KEY;  
        public String VALUE;
        // 构造方法
        private TICKET_PAY_METHOD(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }
        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
    }
	
	/**
	 * 票状态
	 * @author wsh
	 */
	public enum TICKET_STATUS {
		
		// 1:未扫描 未使用  2:扫描了 使用了   3:退票中 4:已取消 5:已退款 6:已过期
		NO_USE(1, "未使用"),
		USEED(2,"已使用"),
		REFUNDING(3,"退票中"),
		CANCELED(4,"已取消"),
		REFUND_OVER(5,"已退款"),
		OVERDUE(6,"已过期");
		
		// 成员变量
        public Integer KEY;  
        public String VALUE;
        // 构造方法
        private TICKET_STATUS(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }
        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
    }
	
	/** 
     * 票据退款记录状态
     * @author wsh
     * 1:待审核 2:审核通过 3：审核不通过 4:退款成功 5:退款失败 
     * */
	public enum TICKET_REFUND_STATUS{
		
		WAIT_AUDIT(1,"待审核"),
		AUDIT_PASS(2,"审核通过"),
		REFUSE_REFUND(3,"拒绝退款"),
		SUCCESS_REFUND(4,"退款成功"),
		IN_REFUND(5,"退款中"),
		ERROR_REFUND(6,"退款失败");
		
		 // 成员变量
        public Integer KEY;  
        public String VALUE;
        // 构造方法
        private TICKET_REFUND_STATUS(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }

        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
	}
	
	/**
	 * 登录类型
	 * @author zzf
	 * 
	 */
	public enum UserAuthEnums {
		QQ(1, "QQ"), SINA(2, "新浪微博"), WEIBO(3, "腾讯微博"), WEIXIN(4, "微信"),KONG(9,"未知"),EMAIL(11,"邮箱登录"),PHONE(12,"手机号码登录"),PHONE_CODE(13,"验证码登录");

		private int id;
		private String name;

		private UserAuthEnums(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		
		public static UserAuthEnums getById(int id){
			for(UserAuthEnums item:values()){
				if(id==item.getId()){
					return item;
				}
			}
			return KONG;
		}
		}
	//票据状态
	public enum OrderTicketStatusEnums {
		OrderTicketStatusNormal(1, "未使用"), OrderTicketStatusHasUsed(2, "已使用"), OrderTicketStatusHasRefund(
				4, "退掉了"),OrderTicketStatusWaitRefund(3,"退款中");
		private int id;
		private String name;

		private OrderTicketStatusEnums(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
	
	/**
	 * 推送消息类型
	 * @author wsh
	 */
	public enum MESSAGE_TYPE {
		
		SYSTEM(1,"系统消息"),
		PARTY(2,"局消息"),
		CHOSEN(3,"精选消息"),
		COMMENT(4,"评论消息");
		
		// 成员变量
        public int KEY;  
        public String VALUE;
        
        // 构造方法
        private MESSAGE_TYPE(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }
        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
    }
	
	/**
	 * 活动状态
	 * @author wsh
	 */
	public enum PARTY_STATUS {
		
		// 1:未开始(正常) 2:进行中  3:已结束  4:已取消  5:隐藏(后台强制下架) 6:已删除
		NORMAL(1,"未开始"),
		IN(2,"进行中"),
		END(3,"已结束"),
		CANCEL(4,"已取消"),
		ABNORMAL(5,"隐藏"),
		DELETE(6,"已删除"),
		AUDITING(7,"审核中"),
		AUDIT_SUCCESS(8,"审核通过"),
		AUDIT_FAILED(9,"审核未通过");
		
		
		// 成员变量
        public int KEY;  
        public String VALUE;
        
        // 构造方法
        private PARTY_STATUS(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }
        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
    }
	
	/**
	 * 评价标签
	 * @author wsh
	 */
	public enum RATE_TYPE {
		
		// 1:气氛不错 2:环境不错 3:准时开始  4:交到朋友 
		CHEER(1,"气氛不错"),
		AROUND(2,"环境不错"),
		ONTIME(3,"准时开始"),
		FRIEND(4,"交到朋友 ");
		
		// 成员变量
        public int KEY;  
        public String VALUE;
        
        // 构造方法
        private RATE_TYPE(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }
        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
    }
	
	/**
	 * 报名状态 1:未报名   2:已报名  3:待付款
	 * @author wsh
	 */
	public enum REGIST_ORDER_STATUS {
		
		REGIST(1,"未报名"),
		REGISTERED(2,"已报名"),
		WAIT_PAY(3,"待付款");
		
		// 成员变量
        public int KEY;  
        public String VALUE;
        
        // 构造方法
        private REGIST_ORDER_STATUS(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }
        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
    }
	
	/**
	 * 活动报名状态
	 * 1:未审核  2:已经通过 3:审核未通过 4: 取消报名
	 * @author wsh
	 */
	public enum REGIST_STATUS {
		
		WAIT_AUDIT(1,"未审核"),
		SUCCESS_AUDIT(2,"已经通过"),
		FAIL_AUDIT(3,"审核未通过"),
		CANCEL_REGIST(4,"取消报名");
		// 成员变量
        public int KEY;  
        public String VALUE;
        // 构造方法
        private REGIST_STATUS(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }
        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
    }
	
	/**
	 * 给客户端返回订单退款状态
	 * 1:无需退款  2:需要退款
	 * @author wsh
	 */
	public enum CLIENT_REFUND_STATUS {
		
		NOT_NEED_REFUND(1,"无需退款"),
		NEED_REFUND(2,"需要退款");
		
		// 成员变量
        public int KEY;  
        public String VALUE;
        // 构造方法
        private CLIENT_REFUND_STATUS(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }
        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
    }
	
	/**
	 * 订单套餐退款方式
	 * 1:全额退款  2:部分退款 3:不可退
	 * @author wsh
	 */
	public enum PIECE_REFUND_WAY {
		
		FULL_REFUND(1,"全额退款"),
		PARTIAL_REFUND(2,"部分退款"),
		NO_REFUND(3,"不可退");
		
		// 成员变量
        public int KEY;  
        public String VALUE;
        // 构造方法
        private PIECE_REFUND_WAY(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }
        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
    }
	
	/**
	 * 活动标签
	 * @author wsh
	 */
	public enum PARTY_LABEL {
		
		// 1:无 2:官方认证  3:官方活动  4:推荐活动
		NULL_PARTY(1,"无"),
		BUSINESS_PARTY(2,"官方认证"),
		OFFICIAL_PARTY(3,"官方活动"),
		RECOMMEND_PARTY(4,"推荐活动");
		
		// 成员变量
        public int KEY;  
        public String VALUE;
        
        // 构造方法
        private PARTY_LABEL(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }
        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
    }
	
	 /** 
     * 票据退款形式
     * @author ZhuZF
     * 
     * */
	public enum TICKET_REFUND_WAY{
		ALL_REFUND(1,"全额退款"),
		PART_REFUND(2,"部分可退"),
		NO_REFUND(3,"不可退");
		 // 成员变量
        public Integer KEY;  
        public String VALUE;
        // 构造方法
        private TICKET_REFUND_WAY(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }

        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
	}
	
	/**
	 * 可结算状态
	 * @author wsh
	 */
	public enum PARTY_IS_BALANCE {
		
		// 1:可结算 2:不可结算
		CAN_BALANCE(1,"可结算"),
		CANNOT_BALANCE(2,"不可结算");
		
		// 成员变量
        public int KEY;  
        public String VALUE;
        
        // 构造方法
        private PARTY_IS_BALANCE(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }
        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
    }
	
	/**
	 * 结算状态
	 * @author wsh
	 */
	public enum PARTY_BALANCE_STATUS {
		
		// 1:未结算 2:已结算
		NO_BALANCE(1,"未结算"),
		BALANCED(2,"已结算");
		
		// 成员变量
        public int KEY;  
        public String VALUE;
        
        // 构造方法
        private PARTY_BALANCE_STATUS(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }
        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
    }
	
	/**
	 * 退款来源 1:用户申请 2:活动取消 3:订单过期
	 * @author wsh
	 */
	public enum REFUND_SOURCE {
		
		// 1:用户申请 2:活动取消 3:订单过期
		USER_APPLY(1,"用户申请"),
		PARTY_CANCEL(2,"活动取消 "),
		OVER_DUE(3,"已结算");
		
		// 成员变量
        public int KEY;  
        public String VALUE;
        
        // 构造方法
        private REFUND_SOURCE(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }
        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
    }
	
	/**
	 * 订单删除状态 1:未删除 2：已删除
	 * @author wsh
	 */
	public enum ORDER_DELETE_STATUS {
		
		// 删除状态_v12 1:未删除 2:已删除
		NO_DELETE(1,"未删除"),
		DELETED(2,"已删除 ");
		
		// 成员变量
        public int KEY;  
        public String VALUE;
        
        // 构造方法
        private ORDER_DELETE_STATUS(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }
        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
    }
	
	/**
	 * 活动来源 1:CMS 2:APP 3:BIZ
	 * @author wsh
	 */
	public enum PARTY_SOURCES {
		
		// 活动来源 1:CMS 2:APP 3:BIZ
		CMS(1,"CMS"),
		APP(2,"APP"),
		BIZ(3,"BIZ");
		
		// 成员变量
        public int KEY;  
        public String VALUE;
        
        // 构造方法
        private PARTY_SOURCES(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }
        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
    }
	
	
	
	 /** 
     * 注册方式
     * @author LiCn
     * 
     * */
	public enum REGIST_WAY{
		PHONE(1,"手机号"),
		QQ(2,"QQ"),
		SINA_WEIBO(3,"新浪微博"),
		QQ_WEIBO(4,"腾讯QQ微博"),
		WEIXIN(5,"微信"),
		CODE_REGIST(6,"验证码注册"),
		H5_REGIST(7,"H5注册");
		 // 成员变量
        public Integer KEY;  
        public String VALUE;
        // 构造方法
        private REGIST_WAY(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }

        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
	}
	
	/**
	 * 交易来源 0:未交易 1:APP 2:H5
	 * @author wsh
	 */
	public enum TRADE_SOURCES {
		
		// 活动来源 1:CMS 2:APP 3:BIZ
		APP(1,"APP"),
		H5(2,"H5");
		
		// 成员变量
        public int KEY;  
        public String VALUE;
        
        // 构造方法
        private TRADE_SOURCES(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }
        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
    }
}

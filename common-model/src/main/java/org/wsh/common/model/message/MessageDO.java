package org.wsh.common.model.message;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.model.base.BaseDO;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  Message模型
* since Date： 2016-11-23 14:56:02
*/
public class MessageDO extends BaseDO{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 消息类型[TO_ONE,TO_MANY]
	 */
	@Setter
	@Getter
	private String type;
		
	/**
	 * 消息主题
	 */
	@Setter
	@Getter
	private String topic;
		
	/**
	 * 发送者
	 */
	@Setter
	@Getter
	private Long fromId;
		
	/**
	 * 目标接收者
	 */
	@Setter
	@Getter
	private Long targetId;
		
	/**
	 * 消息内容
	 */
	@Setter
	@Getter
	private String content;
		
	/**
	 * 消息状态:[WAIT_SEND:待发送，SENDING:发送中,SENDED:已发送,VIEWED:已查看]
	 */
	@Setter
	@Getter
	private String status;
		
	/**
	 * 备注说明
	 */
	@Setter
	@Getter
	private String description;
		
	/**
	 * 版本号(乐观锁)
	 */
	@Setter
	@Getter
	private int version;
		
	/**
	 * 0正常,1删除
	 */
	@Setter
	@Getter
	private int isDeleted;
	
}
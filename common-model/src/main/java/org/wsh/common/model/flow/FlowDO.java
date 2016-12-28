package org.wsh.common.model.flow;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.model.base.BaseDO;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  Flow模型
* since Date： 2016-12-28 11:14:25
*/
public class FlowDO extends BaseDO{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 类型[WITHDRAW:提现,CREDIT:赊账,REFUND:退款]
	 */
	@Setter
	@Getter
	private String type;
		
	/**
	 * 名称
	 */
	@Setter
	@Getter
	private String name;
		
	/**
	 * 描述
	 */
	@Setter
	@Getter
	private String description;
		
	/**
	 * 步骤数量
	 */
	@Setter
	@Getter
	private int stepNum;
		
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
	
	public FlowDO() {
	}

	public FlowDO(String type, String name, String description, int stepNum) {
		this.type = type;
		this.name = name;
		this.description = description;
		this.stepNum = stepNum;
	}

	@Override
	public String toString() {
		return "FlowDO{" +
				"type='" + type + '\'' +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", stepNum=" + stepNum +
				", version=" + version +
				", isDeleted=" + isDeleted +
				'}';
	}
}
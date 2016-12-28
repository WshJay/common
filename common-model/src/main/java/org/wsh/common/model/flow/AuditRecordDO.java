package org.wsh.common.model.flow;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.model.base.BaseDO;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  Auditrecord模型
* since Date： 2016-12-28 11:14:25
*/
public class AuditRecordDO extends BaseDO{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 流程ID
	 */
	@Setter
	@Getter
	private Long flowId;
		
	/**
	 * 文件ID
	 */
	@Setter
	@Getter
	private Long fileId;
		
	/**
	 * 审核状态:[PASS:通过,FAIL:未通过]
	 */
	@Setter
	@Getter
	private String status;
		
	/**
	 * 审核描述
	 */
	@Setter
	@Getter
	private String description;
		
	/**
	 * 步骤
	 */
	@Setter
	@Getter
	private int step;
		
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
		
	/**
	 * 操作人ID
	 */
	@Setter
	@Getter
	private Long operatorUserId;
	
	public AuditRecordDO() {
	}

	public AuditRecordDO(Long flowId, Long fileId, String status, String description, int step, int version, int isDeleted, Long operatorUserId) {
		this.flowId = flowId;
		this.fileId = fileId;
		this.status = status;
		this.description = description;
		this.step = step;
		this.version = version;
		this.isDeleted = isDeleted;
		this.operatorUserId = operatorUserId;
	}
}
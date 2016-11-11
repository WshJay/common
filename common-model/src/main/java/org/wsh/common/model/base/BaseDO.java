package org.wsh.common.model.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有实体类都要继承此抽象类
 * Project:     <mercury-repository>
 * File Name:   <BaseDO.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2015-2-28 下午1:17:46
 */
public abstract class BaseDO implements Serializable {

	private static final long serialVersionUID = -469571077724566638L;

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private Date gmtCreated;

	@Getter
	@Setter
	private Date gmtModified;

	@Getter
	@Setter
	private String beginTime;

	@Getter
	@Setter
	private String endTime;

}

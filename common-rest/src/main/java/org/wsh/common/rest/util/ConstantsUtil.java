package org.wsh.common.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wsh.common.util.properties.PropertiesUtils;


/**
 * 通用变量工具配置类
 * Project:     <common-web>
 * File Name:   <ConstantsUtil.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2016年4月17日 下午8:24:50
 */
public class ConstantsUtil {

	protected static final Logger LOG = LoggerFactory.getLogger(ConstantsUtil.class);
	
	/** 文件地址 */
	private final static String PROPERTISE_PATH = "config/dev/system.properties";
	
	/** APP请求URL */
	public static String SERVER_HOST = PropertiesUtils.readProperties(PROPERTISE_PATH).getProperty("app.server.host");
	
	/** 项目根路径 */
	public static String ROOT_PATH = PropertiesUtils.readProperties(PROPERTISE_PATH).getProperty("app.server.root.path");
	
}

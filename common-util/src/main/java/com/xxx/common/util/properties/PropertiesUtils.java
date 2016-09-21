package com.xxx.common.util.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * 加载配置属性工具类
 * Project:     <common-util>
 * File Name:   <PropertiesUtils.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2016年4月17日 下午3:57:19
 */
public class PropertiesUtils {
	
	protected static final Logger log = LogManager.getLogger(PropertiesUtils.class);
	
	public static Properties readProperties(String properPath) {
		Properties p = new Properties();
		InputStream in = PropertiesUtils.class.getClassLoader()
				.getResourceAsStream(properPath);
		try {
			p.load(in);
		} catch (IOException e) {
			log.error("properties文件读取异常", e);
		} finally {
			try {
				in.close();
				if (null != in) {
					in = null;
				}
			} catch (IOException e) {
				log.error("properties文件读取异常", e);
			}
		}
		return p;
	}

}

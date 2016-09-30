package org.wsh.common.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *   用户获取前台绝对路径
 *   by lu
 * 
 * */
public class FrontUrlUtil {
	/**前台URL*/
	public static String uploadFrontUrl;
	/**前台RealPath*/
	public static String uploadFrontRealPath;
	
	
	static{
		/** 前台项目绝对路径*/
		Properties prop = new Properties();    
		/** 拿到classpath下的文件作为流来读取*/
        InputStream in = FrontUrlUtil.class.getResourceAsStream("/system.properties");    
        try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		uploadFrontUrl=prop.getProperty("upload.frontstage.Url").trim();
        uploadFrontRealPath = prop.getProperty("upload.frontstage.realPath").trim();
	}
	

}


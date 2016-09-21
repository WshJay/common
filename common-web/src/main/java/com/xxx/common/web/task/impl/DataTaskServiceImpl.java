package com.xxx.common.web.task.impl;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.xxx.common.server.service.data.DataService;
import com.xxx.common.util.date.DateUtil;
import com.xxx.common.web.task.DataTaskService;
import com.xxx.common.web.util.ConstantsUtil;

@Service("dataTaskService")
public class DataTaskServiceImpl {
	
	private final Logger log = LoggerFactory.getLogger(DataTaskService.class);
	
	@Autowired
	private DataService dataService;
	
	@Autowired
	private HttpServletRequest request;

	/**
	 * 每天凌晨3点生成用户行为全量表
	 */
	@Scheduled(cron = "0 0 3 * * ?")
	public void generateAllUserDataTask() {
		
		log.info("生成用户行为全量表开始...");
		String exportDate = DateUtil.parseDate(DateUtil.getYesterday0(), DateUtil.YYYY_MM_DD);
		/** 项目根路径*/
//		String rootPath = request.getSession().getServletContext().getRealPath(File.separator);
		String rootPath = ConstantsUtil.ROOT_PATH;
		String startupExcelPath = new StringBuffer(rootPath).append("download").append(File.separator)
				.append(DateUtil.parseDate(DateUtil.getYesterday0(), DateUtil.YYYY))
				.append(File.separator).append(DateUtil.parseDate(DateUtil.getYesterday0(), DateUtil.MM))
				.append(File.separator).append(exportDate).append("-user.xls").toString();
		log.info("文件地址:" + startupExcelPath);
		dataService.generateAllUserData(exportDate, startupExcelPath);
		log.info("生成用户行为全量表结束...");
	}
	
	/**
	 * 获取地址
	 * @return
	 */
	public String getRootPath() {
		String courseFile = "";
		try {
			File directory = new File("");// 参数为空
			courseFile = directory.getCanonicalPath();
			log.info("获取项目地址异常:" + courseFile);
		} catch (IOException e) {
			log.info("获取项目地址异常:",e);
		}
		return courseFile;
	}

}


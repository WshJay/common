package com.xxx.common.web.action.data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xxx.common.repository.dao.app.order.AppPartyOrderDao;
import com.xxx.common.repository.dao.app.user.AppUserInfoDao;
import com.xxx.common.repository.dao.data.TjAppStartupDao;
import com.xxx.common.repository.dao.data.TjButtonClickDao;
import com.xxx.common.repository.entity.base.SimpleMapDO;
import com.xxx.common.repository.entity.data.TjAppStartup;
import com.xxx.common.repository.pagination.Pagination;
import com.xxx.common.server.export.AppActiveDayDO;
import com.xxx.common.server.export.AppStartupDO;
import com.xxx.common.server.service.data.DataService;
import com.xxx.common.util.collections.CollectionUtils;
import com.xxx.common.util.date.DateUtil;
import com.xxx.common.util.export.GenerateExcel;
import com.xxx.common.util.file.FileUtil;
import com.xxx.common.web.util.ConstantsUtil;

/**
 * APP启动请求
 * Project:     <common-data>
 * File Name:   <AppStartupAction.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2016年4月14日 下午12:55:40
 */
@RequestMapping("/data")
@Controller
public class AppStartupAction {

	private static Logger log = LoggerFactory.getLogger(AppStartupAction.class);
	
	@Autowired
	private TjAppStartupDao tjAppStartupDao;
	
	@Autowired
	private TjButtonClickDao tjButtonClickDao;
	
	@Autowired
	private AppUserInfoDao appUserInfoDao;
	
	@Autowired
	private AppPartyOrderDao appPartyOrderDao;
	
	@Autowired
	private DataService dataService;
	
	/**
	 * 日活跃用户
	 * @param tjAppStartup
	 * @param model
	 * @return
	 */
	@RequestMapping("/startup/list")
	public String queryList(TjAppStartup tjAppStartup, Model model){
		
		model.addAttribute("exportDate", DateUtil.parseDate(DateUtil.getYesterday0(), DateUtil.YYYY_MM_DD));
		
		return "/data/startup/list";
	}
	
	/**
	 * 日活跃用户导出
	 * @param request
	 * @param response
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/startup/export")
	public String startupExport(HttpServletRequest request,HttpServletResponse response,String exportDate){
		
		/** 如果传入参数为空则默认为昨天 */
		if (StringUtils.isBlank(exportDate)) {
			exportDate = DateUtil.parseDate(DateUtil.getYesterday0(), DateUtil.YYYY_MM_DD);
		}
		/** 项目根路径*/
//		String rootPath = request.getSession().getServletContext().getRealPath(File.separator);
		String rootPath = ConstantsUtil.ROOT_PATH;
		String startupExcelPath = new StringBuffer(rootPath).append("download").append(File.separator)
				.append(DateUtil.parseDate(DateUtil.getYesterday0(), DateUtil.YYYY))
				.append(File.separator).append(DateUtil.parseDate(DateUtil.getYesterday0(), DateUtil.MM))
				.append(File.separator).append(exportDate).append("-startup.xls").toString();
		log.info("文件地址:" + startupExcelPath);
		if (FileUtil.isFileExist(startupExcelPath)) {
		    try {
	            outToClient(response, startupExcelPath);
			} catch (IOException e) {
				log.error("下载Excel数据异常",e);
			}
		}else{
			TjAppStartup tjAppStartup = new TjAppStartup();
			String beginTime = DateUtil.getDateFormat0(exportDate);
			String endTime = DateUtil.getDateFormat9(exportDate);
			tjAppStartup.setBeginTime(beginTime);
			tjAppStartup.setEndTime(endTime);
			List<AppStartupDO> appStartupDOList = new ArrayList<AppStartupDO>();
			/** 获取点击报名次数 */
			List<String> buttonNameList = new ArrayList<String>();
			buttonNameList.add("我要报名");
			buttonNameList.add("查看报名咨询");
			List<SimpleMapDO> registTimesList = tjButtonClickDao.queryClickTimesListByParams(beginTime, endTime, buttonNameList);
			Map<String, Integer> registTimesMap = CollectionUtils.ListCovertMap("key","value",registTimesList);
			List<SimpleMapDO> startupTimesList = tjAppStartupDao.queryStartupTimesListByTimeFrame(beginTime, endTime);
			Map<String, Integer> startupTimesMap = CollectionUtils.ListCovertMap("key","value",startupTimesList);
			/** 获取用户启动数量 */
			Map<String,AppStartupDO> startupMap = new HashMap<String,AppStartupDO>();
			int totalCount = tjAppStartupDao.queryCountByParams(tjAppStartup);
			Pagination pagination = new Pagination();
			pagination.setPageSize(10000);
			pagination.setTotalCount(totalCount);
			int totalPage = pagination.getTotalPage();
			for (int i = 1; i <= totalPage; i++) {
				pagination.setP(String.valueOf(i));
				pagination.setPageSize(10000);
				pagination.setTotalCount(totalCount);
				List<TjAppStartup> dataList = tjAppStartupDao.queryListByParams(tjAppStartup, pagination.getRowBounds());
				for (TjAppStartup tjAppStartup2 : dataList) {
					String key = tjAppStartup2.getUserId() + ":" + tjAppStartup2.getDeviceNo();
					AppStartupDO appStartupDO = new AppStartupDO();
					appStartupDO.setStartupTime(DateUtil.parseDate(tjAppStartup2.getCreateTime(), DateUtil.YYYY_MM_DD));
					appStartupDO.setDeviceNo(tjAppStartup2.getDeviceNo());
					appStartupDO.setUserId(String.valueOf(tjAppStartup2.getUserId()));
					appStartupDO.setChannel(tjAppStartup2.getChannel());
					startupMap.put(key, appStartupDO);
				}
			}
			for (Entry<String, AppStartupDO> entry : startupMap.entrySet()) {
			   if (registTimesMap.containsKey(entry.getKey())) {
				   entry.getValue().setJoinPartyTimes(registTimesMap.get(entry.getKey()).toString());
				}else{
					entry.getValue().setJoinPartyTimes("0");
				}
			   if (startupTimesMap.containsKey(entry.getKey())) {
				   entry.getValue().setStartupTimes(startupTimesMap.get(entry.getKey()).toString());
				}else{
					entry.getValue().setStartupTimes("0");
				}
			   appStartupDOList.add(entry.getValue());
			 }
			
			XSSFWorkbook wb = GenerateExcel.exportExcel(new AppStartupDO(), appStartupDOList);
			try {
				FileUtil.makeDirectory(startupExcelPath);
				OutputStream out = new FileOutputStream(startupExcelPath);
				wb.write(out);
				outToClient(response, startupExcelPath);
			} catch (IOException e) {
				log.error("下载Excel数据异常",e);
			}
		}
		return null;
	}

	public void outToClient(HttpServletResponse response,
			String startupExcelPath) throws FileNotFoundException, IOException {
		File file = new File(startupExcelPath);
		/** 取得文件名 */
		String filename = file.getName();
		/** 以流的形式下载文件*/
		InputStream fis = new BufferedInputStream(new FileInputStream(startupExcelPath));
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		/** 清空response */
		response.reset();
		/** 设定输出文件头 */
		response.addHeader("Content-Disposition", "attachment;filename=" + filename);
		response.addHeader("Content-Length", "" + file.length());
		OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
		/** 定义输出类型  */
		response.setContentType("application/octet-stream");
		toClient.write(buffer);
		toClient.flush();
		toClient.close();
	}
	
	/**
	 * 全量用户
	 * @param tjAppStartup
	 * @param model
	 * @return
	 */
	@RequestMapping("/user/list")
	public String queryUserList(Model model){
		
		model.addAttribute("exportDate", DateUtil.parseDate(DateUtil.getYesterday0(), DateUtil.YYYY_MM_DD));
		return "/data/user/list";
	}
	
	/**
	 * 全量用户导出
	 * @param request
	 * @param response
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/user/export")
	public String userExport(HttpServletRequest request,HttpServletResponse response,String exportDate){
		
		/** 如果传入参数为空则默认为昨天 */
		if (StringUtils.isBlank(exportDate)) {
			exportDate = DateUtil.parseDate(DateUtil.getYesterday0(), DateUtil.YYYY_MM_DD);
		}
		/** 项目根路径*/
//		String rootPath = request.getSession().getServletContext().getRealPath(File.separator);
		String rootPath = ConstantsUtil.ROOT_PATH;
		String startupExcelPath = new StringBuffer(rootPath).append("download").append(File.separator)
				.append(DateUtil.parseDate(DateUtil.getYesterday0(), DateUtil.YYYY))
				.append(File.separator).append(DateUtil.parseDate(DateUtil.getYesterday0(), DateUtil.MM))
				.append(File.separator).append(exportDate).append("-user.xls").toString();
		log.info("文件地址:" + startupExcelPath);
		if (FileUtil.isFileExist(startupExcelPath)) {
		    try {
	            outToClient(response, startupExcelPath);
			} catch (IOException e) {
				log.error("下载Excel数据异常",e);
			}
		}else{
			try {
				dataService.generateAllUserData(exportDate, startupExcelPath);
				outToClient(response, startupExcelPath);
			} catch (FileNotFoundException e) {
				log.error("下载Excel数据异常",e);
			} catch (IOException e) {
				log.error("下载Excel数据异常",e);
			}
		}
		return null;
	}
	
	/**
	 * 日活跃用户列表
	 * @param tjAppStartup
	 * @param model
	 * @return
	 */
	@RequestMapping("/days/list")
	public String queryAllActiveList(Model model){
		
		model.addAttribute("exportDate", DateUtil.parseDate(DateUtil.getYesterday0(), DateUtil.YYYY_MM_DD));
		return "/data/days/list";
	}
	
	/**
	 * 日活跃用户总表导出
	 * @param request
	 * @param response
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/allActive/export")
	public String exportAllActiveUserNum(HttpServletRequest request,HttpServletResponse response,String exportDate){
		
		/** 如果传入参数为空则默认为昨天 */
		if (StringUtils.isBlank(exportDate)) {
			exportDate = DateUtil.parseDate(DateUtil.getYesterday0(), DateUtil.YYYY_MM_DD);
		}
		/** 项目根路径*/
//		String rootPath = request.getSession().getServletContext().getRealPath(File.separator);
		String rootPath = ConstantsUtil.ROOT_PATH;
		String startupExcelPath = new StringBuffer(rootPath).append("download").append(File.separator)
				.append(DateUtil.parseDate(DateUtil.getYesterday0(), DateUtil.YYYY))
				.append(File.separator).append(DateUtil.parseDate(DateUtil.getYesterday0(), DateUtil.MM))
				.append(File.separator).append(exportDate).append("-active.xls").toString();
		log.info("文件地址:" + startupExcelPath);
		if (FileUtil.isFileExist(startupExcelPath)) {
		    try {
	            outToClient(response, startupExcelPath);
			} catch (IOException e) {
				log.error("下载Excel数据异常",e);
			}
		}else{
			String beginTime = "2016-02-01 00:00:00";
			String endTime = DateUtil.getDateFormat9(exportDate);
			/** 生成一段时间的日期 */
			List<AppActiveDayDO> appActiveDayDOList = new ArrayList<AppActiveDayDO>();
			Date tmpDate = DateUtil.getYesterday9();
			while (tmpDate.after(DateUtil.parseDate(beginTime, DateUtil.YYYY_MM_DD_HH_MM_SS))) {
				AppActiveDayDO appActiveDayDO = new AppActiveDayDO();
				appActiveDayDO.setDate(DateUtil.parseDate(tmpDate, DateUtil.YYYY_MM_DD));
				appActiveDayDOList.add(appActiveDayDO);
				tmpDate = DateUtil.calculateDays(tmpDate, -1);
			}
			/** 获取每天活跃用户数 */
			List<SimpleMapDO> activeNumDaysList = tjAppStartupDao.queryActiveUserNumByTimeFrame(beginTime, endTime);
			Map<String, Integer> activeNumDaysMap =  CollectionUtils.ListCovertMap("key","value",activeNumDaysList);
			/** 获取每天新增用户数 */
			List<SimpleMapDO> newUserNumDaysList = tjAppStartupDao.queryNewStartupByTimeFrame(beginTime, endTime);
			Map<String, Integer> newUserNumDaysMap =  CollectionUtils.ListCovertMap("key","value",newUserNumDaysList);
			/** 获取每天新增注册用户数 */
			List<SimpleMapDO> registNumDaysList = appUserInfoDao.queryRegistNumByTimeFrame(beginTime, endTime);
			Map<String, Integer> registNumDaysMap =  CollectionUtils.ListCovertMap("key","value",registNumDaysList);
			/** 获取每天总启动数 */
			List<SimpleMapDO> startupTimesDaysList = tjAppStartupDao.queryAllStartupTimesByTimeFrame(beginTime, endTime);
			Map<String, Integer> startupTimesDaysMap =  CollectionUtils.ListCovertMap("key","value",startupTimesDaysList);
			/** 获取每天点击报名次数 */
			List<String> buttonNameList = new ArrayList<String>();
			buttonNameList.add("我要报名");
			buttonNameList.add("查看报名咨询");
			List<SimpleMapDO> clickTimesDaysList = tjButtonClickDao.queryDayClickTimesByFarmeTime(beginTime, endTime, buttonNameList);
			Map<String, Integer> clickTimesMap =  CollectionUtils.ListCovertMap("key","value",clickTimesDaysList);
			for (AppActiveDayDO appActiveDayDO : appActiveDayDOList) {
				String key = DateUtil.parseDate(DateUtil.parseDate(appActiveDayDO.getDate(), DateUtil.YYYY_MM_DD), DateUtil.YYYYMMDD);
				if (activeNumDaysMap.containsKey(key)) {//每天活跃用户数
					appActiveDayDO.setActiveNum(String.valueOf(activeNumDaysMap.get(key)));
				}else{
					appActiveDayDO.setActiveNum("0");
				}
				if (newUserNumDaysMap.containsKey(key)) {//每天新增用户数
					appActiveDayDO.setNewUserNum(String.valueOf(newUserNumDaysMap.get(key)));
				}else{
					appActiveDayDO.setNewUserNum("0");
				}
				if (registNumDaysMap.containsKey(key)) {//每天新增注册用户数
					appActiveDayDO.setRegistNum(String.valueOf(registNumDaysMap.get(key)));
				}else{
					appActiveDayDO.setRegistNum("0");
				}
				if (startupTimesDaysMap.containsKey(key)) {//每天总启动数
					appActiveDayDO.setStartupTimes(String.valueOf(startupTimesDaysMap.get(key)));
				}else{
					appActiveDayDO.setStartupTimes("0");
				}
				if (clickTimesMap.containsKey(key)) {//每天点击报名次数
					appActiveDayDO.setJoinPartyTimes(String.valueOf(clickTimesMap.get(key)));
				}else{
					appActiveDayDO.setJoinPartyTimes("0");
				}
			}
		
			XSSFWorkbook wb = GenerateExcel.exportExcel(new AppActiveDayDO(), appActiveDayDOList);
			try {
				FileUtil.makeDirectory(startupExcelPath);
				OutputStream out = new FileOutputStream(startupExcelPath);
				wb.write(out);
				outToClient(response, startupExcelPath);
			} catch (IOException e) {
				log.error("下载Excel数据异常",e);
			}
		}
		return null;
	}

	
	
	
	public static void main(String[] args) {
//		AppUserInfo appUserInfo = new AppUserInfo();
//		appUserInfo.setId(2);
//		List<AppUserInfo> userList = new ArrayList<AppUserInfo>();
//		userList.add(appUserInfo);
//		Map<String, AppUserInfo> map =  CollectionUtils.ListCovertMap("id",userList);
//		for (Entry<String, AppUserInfo> entry : map.entrySet()) {
//			System.out.println(entry.getKey());
//		}
//		System.out.println(DateUtil.parseDate(DateUtil.getYesterday9(), DateUtil.YYYY_MM_DD_HH_MM_SS));
		
		String beginTime = "2016-02-01 00:00:00";
		String endTime = DateUtil.parseDate(DateUtil.getYesterday9(), DateUtil.YYYY_MM_DD_HH_MM_SS);
//		tjAppStartup.setBeginTime(beginTime);
//		tjAppStartup.setEndTime(endTime);
		List<AppActiveDayDO> appActiveDayDOList = new ArrayList<AppActiveDayDO>();
		Date tmpDate = DateUtil.getYesterday9();
		while (tmpDate.after(DateUtil.parseDate(beginTime, DateUtil.YYYY_MM_DD_HH_MM_SS))) {
			AppActiveDayDO appActiveDayDO = new AppActiveDayDO();
			appActiveDayDO.setDate(DateUtil.parseDate(tmpDate, DateUtil.YYYY_MM_DD));
			appActiveDayDOList.add(appActiveDayDO);
			tmpDate = DateUtil.calculateDays(tmpDate, -1);
		}
		for (AppActiveDayDO appActiveDayDO : appActiveDayDOList) {
			System.out.println(appActiveDayDO.getDate());
		}
		
	}

	
}


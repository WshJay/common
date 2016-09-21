package com.xxx.common.server.service.data.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.common.repository.dao.app.order.AppPartyOrderDao;
import com.xxx.common.repository.dao.app.user.AppUserInfoDao;
import com.xxx.common.repository.dao.data.TjAppStartupDao;
import com.xxx.common.repository.dao.data.TjButtonClickDao;
import com.xxx.common.repository.entity.app.user.AppUserInfo;
import com.xxx.common.repository.entity.base.SimpleMapDO;
import com.xxx.common.repository.entity.data.TjAppStartup;
import com.xxx.common.repository.entity.data.TjPartyClick;
import com.xxx.common.repository.pagination.Pagination;
import com.xxx.common.server.export.AppActiveUserDO;
import com.xxx.common.server.export.AppPartyPvUvWeekDO;
import com.xxx.common.server.service.data.DataService;
import com.xxx.common.util.collections.CollectionUtils;
import com.xxx.common.util.date.DateUtil;
import com.xxx.common.util.export.GenerateExcel;
import com.xxx.common.util.file.FileUtil;

@Service("dataService")
public class DataServiceImpl implements DataService{
	
	private static Logger log = LoggerFactory.getLogger(DataServiceImpl.class);
	
	@Autowired
	private TjAppStartupDao tjAppStartupDao;
	
	@Autowired
	private TjButtonClickDao tjButtonClickDao;
	
	@Autowired
	private AppUserInfoDao appUserInfoDao;
	
	@Autowired
	private AppPartyOrderDao appPartyOrderDao;
	
//	@Autowired
//	private TjDataDao tjDataDao;

	@Override
	public void generateAllUserData(String exportDate, String startupExcelPath) {
		TjAppStartup tjAppStartup = new TjAppStartup();
		String beginTime = "2016-02-01 00:00:00";
		String endTime = DateUtil.getDateFormat9(exportDate);
		/** 获取用户订单购买数量 */
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(6);
		statusList.add(10);
		statusList.add(11);
		statusList.add(12);
		statusList.add(13);
		List<SimpleMapDO> userBuyNumberList = appPartyOrderDao.queryBuyNumberByStatusList(statusList);
		Map<String, Integer> buyNumberMap = CollectionUtils.ListCovertMap("key","value",userBuyNumberList);
		/** 倒推30天 */
		String beginTimeMonth = DateUtil.getDateFormat0(DateUtil.parseDate(DateUtil.calculateDays(new Date(), -30), DateUtil.YYYY_MM_DD));
		/** 获取用户启动天数 */
		List<SimpleMapDO> startupDaysList = tjAppStartupDao.queryStartupDaysByTimeFrame(beginTimeMonth, endTime);
		Map<String, Integer> startupDaysMap =  CollectionUtils.ListCovertMap("key","value",startupDaysList);
		/** 获取用户启动总表 */
		Map<String,AppActiveUserDO> startupMap = new HashMap<String,AppActiveUserDO>();
		tjAppStartup.setBeginTime(beginTime);
		tjAppStartup.setEndTime(endTime);
		Set<Integer> userIdSet = new HashSet<Integer>();
		List<AppActiveUserDO> appActiveUserDOList = new ArrayList<AppActiveUserDO>();
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
			for (TjAppStartup appStartup : dataList) {
				userIdSet.add(appStartup.getUserId());
				AppActiveUserDO appActiveUserDO = new AppActiveUserDO();
				appActiveUserDO.setUserId(String.valueOf(appStartup.getUserId()));
				appActiveUserDO.setDeviceNo(appStartup.getDeviceNo());
				String key = appStartup.getUserId() + ":" + appStartup.getDeviceNo();
				startupMap.put(key, appActiveUserDO);
			}
		}
		List<AppUserInfo> userList = appUserInfoDao.queryListByUserIdList(new ArrayList<Integer>(userIdSet));
		Map<String, AppUserInfo> userMap =  CollectionUtils.ListCovertMap("id", userList);
		for (Entry<String, AppActiveUserDO> entry : startupMap.entrySet()) {
			AppActiveUserDO appActiveUserDO = entry.getValue();
			if (Integer.parseInt(appActiveUserDO.getUserId()) != 0) {
				if (userMap.containsKey(appActiveUserDO.getUserId())) {
					appActiveUserDO.setCityName(userMap.get(appActiveUserDO.getUserId()).getCityName());
					appActiveUserDO.setRegistTime(DateUtil.parseDate(userMap.get(appActiveUserDO.getUserId()).getRegistTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
					appActiveUserDO.setLastLoginTime(DateUtil.parseDate(userMap.get(appActiveUserDO.getUserId()).getLastTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
					if (userMap.get(appActiveUserDO.getUserId()).getSex() == 1) {
						appActiveUserDO.setSex("男");
					}else if(userMap.get(appActiveUserDO.getUserId()).getSex() == 2){
						appActiveUserDO.setSex("女");
					}else{
						appActiveUserDO.setSex("未填写");
					}
					appActiveUserDO.setMobile(userMap.get(appActiveUserDO.getUserId()).getMobile());
				}
				if (buyNumberMap.containsKey(appActiveUserDO.getUserId())) {
					appActiveUserDO.setBuyNum(String.valueOf(buyNumberMap.get(String.valueOf(appActiveUserDO.getUserId()))));
				}else{
					appActiveUserDO.setBuyNum("0");
				}
			}
			if (startupDaysMap.containsKey(entry.getKey())) {
				appActiveUserDO.setStartupDayNum(String.valueOf(startupDaysMap.get(entry.getKey())));
			}else{
				appActiveUserDO.setStartupDayNum("0");
			}
			appActiveUserDOList.add(appActiveUserDO);
		}
		XSSFWorkbook wb = GenerateExcel.exportExcel(new AppActiveUserDO(), appActiveUserDOList);
		try {
			FileUtil.makeDirectory(startupExcelPath);
			OutputStream out = new FileOutputStream(startupExcelPath);
			wb.write(out);
		} catch (IOException e) {
			log.error("生成Excel数据异常",e);
		}
	}

//	@Override
//	public void generateAllPartyClickData(String exportBeginDate,
//			String exportEndDate, String startupExcelPath) {
//		
//		List<TjPartyClick> partyClickList =tjDataDao.selectTjPartyClickListByTime("2016-04-10","2016-04-11");
//		if(null !=partyClickList){
//			for(TjPartyClick tjPartyClick:partyClickList){
//				TjPartyClick tpc=tjDataDao.selectUvCpvByParty(tjPartyClick);
//				tjPartyClick.setUv(tpc.getUv());
//				tjPartyClick.setCpv(tpc.getCpv());
//			}
//			List<AppPartyPvUvWeekDO> appPartyPvUvWeekDOList  = new ArrayList<AppPartyPvUvWeekDO>();
//			for(TjPartyClick tjPartyClick2:partyClickList){
//				AppPartyPvUvWeekDO appPartyPvUvWeekDO = new AppPartyPvUvWeekDO();
//				appPartyPvUvWeekDO.setDate(DateUtil.parseDate(tjPartyClick2.getCreateTime(), DateUtil.YYYY_MM_DD));
//				appPartyPvUvWeekDO.setPartyId(String.valueOf(tjPartyClick2.getPartyId()));
//				appPartyPvUvWeekDO.setPartyName(tjPartyClick2.getPartyName());
//				appPartyPvUvWeekDO.setCityName(tjPartyClick2.getDestCityName());
//				appPartyPvUvWeekDO.setTagName(tjPartyClick2.getTagName());
//				appPartyPvUvWeekDO.setPV(String.valueOf(tjPartyClick2.getPv()));
//				appPartyPvUvWeekDO.setUV(String.valueOf(tjPartyClick2.getUv()));
//				appPartyPvUvWeekDO.setJoinPartyTimes(String.valueOf(tjPartyClick2.getCpv()));
//				appPartyPvUvWeekDOList.add(appPartyPvUvWeekDO);
//			}
//		}
//	}

}


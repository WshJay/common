package com.duoju.daq.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.duoju.daq.model.ResponseModel;
import com.duoju.daq.util.DateUtil;
import com.duoju.daq.util.EMUtil;
import com.duoju.daq.util.FastJsonUtil;
import com.duoju.daq.util.WebUtil;
import com.xxx.common.repository.dao.app.system.AppBannerClickDao;
import com.xxx.common.repository.dao.app.user.AppUserLoginRecordDao;
import com.xxx.common.repository.dao.data.TjAppStartupDao;
import com.xxx.common.repository.dao.data.TjArticlesClickDao;
import com.xxx.common.repository.dao.data.TjAuthorClickDao;
import com.xxx.common.repository.dao.data.TjButtonClickDao;
import com.xxx.common.repository.dao.data.TjPartyClickDao;
import com.xxx.common.repository.dao.data.TjPlayClickDao;
import com.xxx.common.repository.dao.data.TjUserBehaviorDao;
import com.xxx.common.repository.dao.data.TjUserRegistCodeDao;
import com.xxx.common.repository.dao.data.TjUserStayDao;
import com.xxx.common.repository.dao.data.TjWidgetClickDao;
import com.xxx.common.repository.entity.app.system.AppBannerClick;
import com.xxx.common.repository.entity.app.user.AppUserLoginRecord;
import com.xxx.common.repository.entity.data.TjAppStartup;
import com.xxx.common.repository.entity.data.TjArticlesClick;
import com.xxx.common.repository.entity.data.TjAuthorClick;
import com.xxx.common.repository.entity.data.TjButtonClick;
import com.xxx.common.repository.entity.data.TjPartyClick;
import com.xxx.common.repository.entity.data.TjPlayClick;
import com.xxx.common.repository.entity.data.TjUserBehavior;
import com.xxx.common.repository.entity.data.TjUserRegistCode;
import com.xxx.common.repository.entity.data.TjUserStay;
import com.xxx.common.repository.entity.data.TjWidgetClick;

/**
 * 统计埋点
 * Project:     <common-daq>
 * File Name:   <TjController.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2016年4月23日 下午3:45:40
 */
@Controller
@RequestMapping("/tj")
public class TjController {
	
	@Resource
	private TjPartyClickDao tjPartyClickDao;
	
	@Resource
	private TjArticlesClickDao tjArticlesClickDao;
	
	@Resource
	private TjButtonClickDao tjButtonClickDao;
	
	@Resource
	private TjAppStartupDao tjAppStartupDao;
	
	@Resource
	private TjPlayClickDao tjPlayClickDao;
	
	@Resource
	private TjWidgetClickDao tjWidgetClickDao;
	
	@Resource
	private TjAuthorClickDao tjAuthorClickDao;
	
	@Resource
	private TjUserStayDao tjUserStayDao;
	
	@Resource
	private TjUserRegistCodeDao tjUserRegistCodeDao;
	
	@Resource
	private TjUserBehaviorDao tjUserBehaviorDao;
	
	@Resource
	private AppBannerClickDao bannerClickDao;
	
	@Resource
	private AppUserLoginRecordDao appUserLoginRecordDao;
	
    private static Logger log = LoggerFactory.getLogger(TjController.class);
    
	/**
	 * 用户停留访问离开接口
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user_stay_leave", method = {RequestMethod.POST, RequestMethod.GET })
	public String userStayLeave(@Param("json") String json,HttpServletResponse response){
		
		//判断json参数是否为空 json为null
		if(!WebUtil.validateRequest(json,"userId","deviceNo","deviceType","appNo","types")) return WebUtil.jsonErrorResult();
		//json转map
		TjUserStay tjUserStay = FastJsonUtil.jsonToObject(json, TjUserStay.class);
		tjUserStay.setLeaveTime(new Date());
		if(null !=tjUserStay.getTypes()){
			String types =tjUserStay.getTypes();
			String[] typesa=types.split(",");
			for(int i=0;i<typesa.length;i++){
				tjUserStay.setType(Integer.parseInt(typesa[i]));
				TjUserStay maxUserStay = tjUserStayDao.loadMaxIntoTime(tjUserStay.getDeviceNo(), tjUserStay.getType());
				Date intoTime = maxUserStay.getIntoTime();
				Date leaveTime=tjUserStay.getLeaveTime();
				tjUserStay.setId(maxUserStay.getId());
				//比较离开时间进入时间有没有隔天
				try {
					//当天
					if(DateUtil.daysBetween(intoTime, leaveTime) ==0){
						Long seconds=DateUtil.getDifference(intoTime,leaveTime);
						tjUserStay.setLeaveTime(leaveTime);
						tjUserStay.setStayLong(seconds.intValue());
						mapPool.put(createKey(), new Data("userStay_update",tjUserStay));
						startThread();
						//更新数据
//						tjUserStayDao.updateStayLongById(tjUserStay.getId(), leaveTime, seconds.intValue());
						
					}else{
						//第一段更新
						String todayEnd=new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(intoTime);
						tjUserStay.setLeaveTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(todayEnd));
						Long seconds=DateUtil.getDifference(intoTime,tjUserStay.getLeaveTime());
						tjUserStay.setLeaveTime(leaveTime);
						tjUserStay.setStayLong(seconds.intValue());
						mapPool.put(createKey(), new Data("userStay_update",tjUserStay));
						startThread();
//						tjUserStayDao.updateStayLongById(tjUserStay.getId(), leaveTime, seconds.intValue());
						
						//第二段添加
						String leaveStart=new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(leaveTime);
						tjUserStay.setIntoTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(leaveStart));
						tjUserStay.setLeaveTime(leaveTime);
						Long seconds2=DateUtil.getDifference(tjUserStay.getIntoTime(),leaveTime);
						tjUserStay.setStayLong(seconds2.intValue());
//						tjUserStayDao.insert(tjUserStay);
						mapPool.put(createKey(), new Data("userStay_insert",tjUserStay));
						startThread();
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		//返回 json
		return WebUtil.responseResult(new ResponseModel());
	}
	
	/**
	 * 用户停留访问进入接口
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user_stay_into", method = {RequestMethod.POST, RequestMethod.GET })
	public String userStayInto(@Param("json") String json,HttpServletResponse response){
		
		//判断json参数是否为空 json为null
		if(!WebUtil.validateRequest(json,"userId","deviceNo","deviceType","appNo","types")) return WebUtil.jsonErrorResult();
		
		//json转map
		TjUserStay tjUserStay = FastJsonUtil.jsonToObject(json, TjUserStay.class);
		tjUserStay.setIntoTime(new Date());
		if(null !=tjUserStay.getTypes()){
			String types =tjUserStay.getTypes();
			String[] typesa=types.split(",");
			for(int i=0;i<typesa.length;i++){
				tjUserStay.setType(Integer.parseInt(typesa[i]));
				mapPool.put(createKey(), new Data("userStay_insert",tjUserStay));
				startThread();
//				tjUserStayDao.insert(tjUserStay);
			}
		}
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		//返回 json
		return WebUtil.responseResult(new ResponseModel());
	}
	
	
	/**
	 * 精选作者点击量统计
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/author_click", method = {RequestMethod.POST, RequestMethod.GET })
	public String author_click(@Param("json") String json,HttpServletResponse response){
		//判断json参数是否为空 json为null
		if(!WebUtil.validateRequest(json,"userId","articlesId","authorId","authorName","deviceNo","deviceType")) return WebUtil.jsonErrorResult();
		
		//json转map
		TjAuthorClick tjAuthorClick = FastJsonUtil.jsonToObject(json, TjAuthorClick.class);
		
		tjAuthorClick.setCreateTime(new Date());
		mapPool.put(createKey(), new Data("authorClick_insert",tjAuthorClick));
		startThread();
//		tjAuthorClickDao.insert(tjAuthorClick);
		response.setHeader("Access-Control-Allow-Origin", "*");
		//返回 json
		return WebUtil.responseResult(new ResponseModel());
	}
	
	
	/**
	 * widget点击量统计
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/widget_click", method = {RequestMethod.POST, RequestMethod.GET })
	public String widget_click(@Param("json") String json,HttpServletResponse response){
		//判断json参数是否为空 json为null
		if(!WebUtil.validateRequest(json,"userId","widgetId","widgetName","targetId","position","type","deviceNo","deviceType")) return WebUtil.jsonErrorResult();
		//json转map
		TjWidgetClick tjWidgetClick = FastJsonUtil.jsonToObject(json, TjWidgetClick.class);
		tjWidgetClick.setCreateTime(new Date());
		mapPool.put(createKey(), new Data("widgetClick_insert",tjWidgetClick));
		startThread();
//		tjWidgetClickDao.insert(tjWidgetClick);
		response.setHeader("Access-Control-Allow-Origin", "*");
		//返回 json
		return WebUtil.responseResult(new ResponseModel());
	}
	
	
	/**
	 * 音视频播放键统计
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/play_click", method = {RequestMethod.POST, RequestMethod.GET })
	public String play_click(@Param("json") String json,HttpServletResponse response){
		//判断json参数是否为空 json为null
		if(!WebUtil.validateRequest(json,"userId","partyId","partyName","templateId","type","pageType","deviceNo","deviceType")) return WebUtil.jsonErrorResult();
		
		//json转map
		TjPlayClick tjPlayClick = FastJsonUtil.jsonToObject(json, TjPlayClick.class);
		tjPlayClick.setCreateTime(new Date());
		mapPool.put(createKey(), new Data("playClick_insert",tjPlayClick));
		startThread();
//		tjPlayClickDao.insert(tjPlayClick);
		response.setHeader("Access-Control-Allow-Origin", "*");
		//返回 json
		return WebUtil.responseResult(new ResponseModel());
	}
	
	
	/**
	 * 活动浏览量统计
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/party_click", method = {RequestMethod.POST, RequestMethod.GET })
	public String party_click(@Param("json") String json,HttpServletResponse response){
		//判断json参数是否为空 json为null
		if(!WebUtil.validateRequest(json,"userId","partyId","partyName","deviceNo","deviceType")) return WebUtil.jsonErrorResult();
		
		//json转map
		TjPartyClick tjPartyClick = FastJsonUtil.jsonToObject(json, TjPartyClick.class);
		
		tjPartyClick.setCreateTime(new Date());
		mapPool.put(createKey(), new Data("partyClick_insert",tjPartyClick));
		startThread();
//		tjPartyClickDao.insert(tjPartyClick);
		response.setHeader("Access-Control-Allow-Origin", "*");
		//返回 json
		return WebUtil.responseResult(new ResponseModel());
	}
	
	/**
	 * 精选浏览量统计
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/articles_click", method = {RequestMethod.POST, RequestMethod.GET })
	public String articles_click(@Param("json") String json,HttpServletResponse response){
		//判断json参数是否为空 json为null
		if(!WebUtil.validateRequest(json,"userId","articlesId","articlesName","deviceNo","deviceType")) return WebUtil.jsonErrorResult();
		
		//json转map
		TjArticlesClick tjArticlesClick = FastJsonUtil.jsonToObject(json, TjArticlesClick.class);
		tjArticlesClick.setCreateTime(new Date());
		mapPool.put(createKey(), new Data("articlesClick_insert",tjArticlesClick));
		startThread();
//		tjArticlesClickDao.insert(tjArticlesClick);
		response.setHeader("Access-Control-Allow-Origin", "*");
		//返回 json
		return WebUtil.responseResult(new ResponseModel());
	}
	
	
	
	/**
	 * 按钮点击量统计
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/button_click", method = {RequestMethod.POST, RequestMethod.GET })
	public String button_click(@Param("json") String json,HttpServletResponse response){
		//判断json参数是否为空 json为null
		if(!WebUtil.validateRequest(json,"userId","targetId","buttonName","type","deviceNo","deviceType")) return WebUtil.jsonErrorResult();
		
		//json转map
		TjButtonClick tjButtonClick = FastJsonUtil.jsonToObject(json, TjButtonClick.class);
		tjButtonClick.setCreateTime(new Date());
		mapPool.put(createKey(), new Data("buttonClick_insert",tjButtonClick));
		startThread();
//		tjButtonClickDao.insert(tjButtonClick);
		response.setHeader("Access-Control-Allow-Origin", "*");
		//返回 json
		return WebUtil.responseResult(new ResponseModel());
	}
	
	/**
	 * APP启动统计
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/app_startup", method = {RequestMethod.POST, RequestMethod.GET })
	public String app_startup(@RequestParam("json") String json){
		//判断json参数是否为空 json为null
		if(!WebUtil.validateRequest(json,"userId","deviceNo","deviceType")) return WebUtil.jsonErrorResult();
		
		//json转map
		TjAppStartup tjAppStartup = FastJsonUtil.jsonToObject(json, TjAppStartup.class);
		tjAppStartup.setCreateTime(new Date());
		//判断是否是新的
		int m = tjAppStartupDao.selectByDeviceNo(tjAppStartup.getDeviceNo());
		if(m > 0){
			tjAppStartup.setIsNew(2);
		}else{
			tjAppStartup.setIsNew(1);
		}
		mapPool.put(createKey(), new Data("appStartup_insert",tjAppStartup));
		startThread();
//		tjAppStartupDao.insert(tjAppStartup);
		//返回 json
		return WebUtil.responseResult(new ResponseModel());
	}
	
	/**
	 * 验证码登录
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/login_byphone",method={RequestMethod.POST,RequestMethod.GET})
	public String loginByphone(@RequestParam("json") String json) {
		//有必须要验证的参数
		if(!WebUtil.validateRequest(json,"phone","userId")) return WebUtil.jsonErrorResult();
		//json转map
		Map<String, Object> map = FastJsonUtil.jsonToObject(json, Map.class);
		ResponseModel responseModel=new ResponseModel();
		//业务代码
		String phone = (String)WebUtil.getParameter(map, "phone");
		Integer userId = Integer.parseInt(WebUtil.getParameter(map, "userId"));
		
		//记录用户登录信息
		AppUserLoginRecord appUserLoginRecord= new AppUserLoginRecord();
		appUserLoginRecord.setUserId(userId);
		appUserLoginRecord.setPhone(phone);
		appUserLoginRecord.setLoginTime(new Date());
		appUserLoginRecord.setDeviceNo((String)WebUtil.getParameter(map, "deviceNo"));
		appUserLoginRecord.setDeviceType((String)WebUtil.getParameter(map, "deviceType"));
		appUserLoginRecord.setSystemNo((String)WebUtil.getParameter(map, "systemNo"));
		appUserLoginRecord.setAppNo((String)WebUtil.getParameter(map, "appNo"));
		appUserLoginRecord.setLoginType(EMUtil.UserAuthEnums.PHONE_CODE.getId());
		mapPool.put(createKey(), new Data("appUserLoginRecord_insert",appUserLoginRecord));
		startThread();
//		appUserLoginRecordDao.insertUserLogin(appUserLoginRecord);
		//返回 json
		return WebUtil.responseResult(responseModel);
	}
	
	
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/member/login",method={RequestMethod.POST,RequestMethod.GET})
	public String login(@RequestParam("json") String json) {
		//有必须要验证的参数
		if(!WebUtil.validateRequest(json,"name","password")) return WebUtil.jsonErrorResult();
		//json转map
		Map<String, Object> map = FastJsonUtil.jsonToObject(json, Map.class);
		ResponseModel responseModel=new ResponseModel();
		//业务代码
		String name = (String)WebUtil.getParameter(map, "name");
		Integer userId = Integer.parseInt(WebUtil.getParameter(map, "userId"));
		//密码标记
		
			//记录用户登录信息
			AppUserLoginRecord appUserLoginRecord= new AppUserLoginRecord();
			appUserLoginRecord.setUserId(userId);
			appUserLoginRecord.setPhone(name);
			appUserLoginRecord.setLoginTime(new Date());
			appUserLoginRecord.setDeviceNo((String)WebUtil.getParameter(map, "deviceNo"));
			appUserLoginRecord.setDeviceType((String)WebUtil.getParameter(map, "deviceType"));
			appUserLoginRecord.setSystemNo((String)WebUtil.getParameter(map, "systemNo"));
			appUserLoginRecord.setAppNo((String)WebUtil.getParameter(map, "appNo"));
			if(name.contains("@")){
				appUserLoginRecord.setLoginType(EMUtil.UserAuthEnums.EMAIL.getId());
			}else{
				appUserLoginRecord.setLoginType(EMUtil.UserAuthEnums.PHONE.getId());
			}
			mapPool.put(createKey(), new Data("appUserLoginRecord_insert",appUserLoginRecord));
			startThread();
//			appUserLoginRecordDao.insertUserLogin(appUserLoginRecord);
		
		//返回 json
		return WebUtil.responseResult(responseModel);
	}
	
	
	/**
	 * 三方登录
	 * @param json
	 * @return
	 */
	@RequestMapping(value="/user_login_sanfang",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String user_login_sanfang(@RequestParam("json") String json){
		if(!WebUtil.validateRequest(json,"openId","type","userId")) 
		return WebUtil.jsonErrorResult();
		ResponseModel responseModel = new ResponseModel();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = FastJsonUtil.jsonToObject(json, Map.class);
		
		if (map != null) {
			Integer userId = Integer.parseInt(WebUtil.getParameter(map, "userId"));
			Integer type = Integer.parseInt(WebUtil.getParameter(map, "type"));
			/**
			 * 记录用户登录注册信息
			 */
			AppUserLoginRecord appUserLoginRecord= new AppUserLoginRecord();
			appUserLoginRecord.setUserId(userId);
			appUserLoginRecord.setLoginTime(new Date());
			appUserLoginRecord.setDeviceNo((String)WebUtil.getParameter(map, "deviceNo"));
			appUserLoginRecord.setDeviceType((String)WebUtil.getParameter(map, "deviceType"));
			appUserLoginRecord.setSystemNo((String)WebUtil.getParameter(map, "systemNo"));
			appUserLoginRecord.setAppNo((String)WebUtil.getParameter(map, "appNo"));
			appUserLoginRecord.setLoginType(type);
			appUserLoginRecord.setOpenId((String)WebUtil.getParameter(map, "openId"));
			mapPool.put(createKey(), new Data("appUserLoginRecord_insert",appUserLoginRecord));
			startThread();
//			appUserLoginRecordDao.insertUserLogin(appUserLoginRecord);
		}
		return WebUtil.responseResult(responseModel);
	}
	
	
	
	/**
	 * 获取注册验证码
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/member/register/getCode",method={RequestMethod.POST,RequestMethod.GET})
	public String getCode(@RequestParam("json") String json) {
		//没有必须要验证的参数
		if(!WebUtil.validateRequest(json,"phone")) return WebUtil.jsonErrorResult();
		//json转map
		Map<String, Object> map = FastJsonUtil.jsonToObject(json, Map.class);
		ResponseModel responseModel=new ResponseModel();
		//业务代码
		String phone =  (String)WebUtil.getParameter(map, "phone");
		int type = Integer.parseInt(WebUtil.getParameter(map, "type", 1));
	
		TjUserRegistCode tjUserRegistCode= new TjUserRegistCode();
		tjUserRegistCode.setDeviceNo((String)WebUtil.getParameter(map, "deviceNo"));
		tjUserRegistCode.setMobile(phone);
		tjUserRegistCode.setDeviceType((String)WebUtil.getParameter(map, "deviceType"));
		tjUserRegistCode.setCreateTime(new Date());
		if(type==1){
			tjUserRegistCode.setType(type);
			tjUserRegistCode.setButtonName("获取短信验证码");
		}else if(type==2){
			tjUserRegistCode.setType(type);
			tjUserRegistCode.setButtonName("获取语音验证码");
		}
		mapPool.put(createKey(), new Data("userRegistCode_insert",tjUserRegistCode));
		startThread();
//		tjUserRegistCodeDao.insert(tjUserRegistCode);
		//返回 json
		return WebUtil.responseResult(responseModel);
	}
	
	
	/**
	 * 用户行为页面 进来以后调此接口
	 * @param json
	 * @return
	 */
	@RequestMapping(value="/behavior",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String behavior(@RequestParam("json") String json){
		//判断json参数是否为空 json为null
		if(!WebUtil.validateRequest(json,"type")) return WebUtil.jsonErrorResult();
		//json转map
		TjUserBehavior tjUserBehavior = FastJsonUtil.jsonToObject(json, TjUserBehavior.class);
		tjUserBehavior.setCreateTime(new Date());
		tjUserBehavior.setStatus(1);
		tjUserBehaviorDao.insert(tjUserBehavior);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("behaviorId", tjUserBehavior.getId());
		return WebUtil.responseResult(map,new ResponseModel());
	}
	
	
	/**
	 * 更新用户行为状态
	 * @param json
	 * @return
	 */
	@RequestMapping(value="/update_behavior",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String update_behavior(@RequestParam("json") String json){
		//判断json参数是否为空 json为null
		if(!WebUtil.validateRequest(json,"behaviorId")) return WebUtil.jsonErrorResult();
		//json转map
		Map<String,Object> map = FastJsonUtil.jsonToObject(json, Map.class);
		Integer behaviorId =Integer.parseInt(map.get("behaviorId").toString());
		tjUserBehaviorDao.updateBehavior(behaviorId);
		return WebUtil.responseResult(new ResponseModel());
	}
	
	
	@RequestMapping(value="/insert_banner_click",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String insert_banner_click(@RequestParam("json") String json ,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		if(!WebUtil.validateRequest(json,"targetId","type","deviceNo")) 
		return WebUtil.jsonErrorResult();
		@SuppressWarnings("unchecked")
		Map<String,Object> map = FastJsonUtil.jsonToObject(json, Map.class);
		AppBannerClick bannerClick = new AppBannerClick();
		bannerClick.setCreateTime(new Date());
		bannerClick.setTargetId(Integer.parseInt(map.get("targetId").toString()));
		bannerClick.setDeviceNo(map.get("deviceNo").toString());
		bannerClick.setType(Integer.parseInt(map.get("type").toString()));
		bannerClick.setDeviceType(map.get("deviceType").toString());
		if(map.get("userId") != null){
			bannerClick.setAppUserInfoId(Integer.parseInt(map.get("userId").toString()));
		}
		if(map.get("subType") != null){
			bannerClick.setSubType(map.get("subType").toString());
		}
		mapPool.put(createKey(), new Data("bannerClick_insert",bannerClick));
		startThread();
//		bannerClickDao.insert(bannerClick);
		return WebUtil.responseResult(new ResponseModel());
	}
	
	/**
	 * 创建一个KEY
	 * @return
	 */
	public synchronized String createKey() {
		return String.valueOf(number.getAndAdd(1));
	}
	/**
	 * 缓存对象 map
	 */
	@SuppressWarnings("unchecked")
	public static CachePool<String, Object> mapPool = CachePool.getInstance();
	
	// 定义原子变量
	private AtomicInteger number = new AtomicInteger(0);

	private static final int NTHREADS = 5;
	
	// 使用线程池来避免 为每个请求创建一个线程。
	private static final Executor threadPool = Executors
			.newFixedThreadPool(NTHREADS);

	public void startThread() {
		threadPool.execute(new Runnable() {
			public void run() {
				executeCodeOper();
			}
		});
	}

	@SuppressWarnings("rawtypes")
	public void executeCodeOper() {
		String key = "";
		Data param = null;
		synchronized (mapPool) {
			log.info(Thread.currentThread().getName() + "进来了。。。。");
			log.info("现在队列中共有----" + mapPool.size() + "---条数据");
			Iterator it = mapPool.keySet().iterator();
			// 缓存不为空时,取出一个值
			while (it.hasNext()) {
				key = (String) it.next();
				param = (Data)mapPool.get(key);
			}
			if (null != param) {
				// 为防止重复,将其移除
				mapPool.remove(key);
			}
		}

		if (null != param) {
			boolean result = executionMethod(param);
			log.info("此条数据处理========" + result);
			if (!result) {
				// 若处理失败,重新放回队列
				mapPool.put(key, param);
			};
		}
	}

	@SuppressWarnings("rawtypes")
	public boolean executionMethod(Data data) {
		if (data.getKey().equals("userStay_update")) {
			TjUserStay tjUserStay = (TjUserStay)data.getExecutionDO();
			tjUserStayDao.updateStayLongById(tjUserStay.getId(), tjUserStay.getLeaveTime(), tjUserStay.getStayLong());
		}else if (data.getKey().equals("userStay_insert")) {
			TjUserStay tjUserStay = (TjUserStay)data.getExecutionDO();
			tjUserStayDao.insert(tjUserStay);
		}else if (data.getKey().equals("authorClick_insert")) {
			TjAuthorClick tjAuthorClick = (TjAuthorClick)data.getExecutionDO();
			tjAuthorClickDao.insert(tjAuthorClick);
		}else if(data.getKey().equals("widgetClick_insert")) {
			TjWidgetClick tjWidgetClick = (TjWidgetClick)data.getExecutionDO();
			tjWidgetClickDao.insert(tjWidgetClick);
		}else if(data.getKey().equals("playClick_insert")) {
			TjPlayClick tjPlayClick = (TjPlayClick)data.getExecutionDO();
			tjPlayClickDao.insert(tjPlayClick);
		}else if(data.getKey().equals("partyClick_insert")) {
			TjPartyClick tjPartyClick = (TjPartyClick)data.getExecutionDO();
			tjPartyClickDao.insert(tjPartyClick);
		}else if(data.getKey().equals("articlesClick_insert")) {
			TjArticlesClick tjArticlesClick = (TjArticlesClick)data.getExecutionDO();
			tjArticlesClickDao.insert(tjArticlesClick);
		}else if (data.getKey().equals("buttonClick_insert")) {
			TjButtonClick tjButtonClick = (TjButtonClick)data.getExecutionDO();
			tjButtonClickDao.insert(tjButtonClick);
		}else if (data.getKey().equals("appStartup_insert")) {
			TjAppStartup tjAppStartup = (TjAppStartup)data.getExecutionDO();
			tjAppStartupDao.insert(tjAppStartup);
		}else if (data.getKey().equals("appUserLoginRecord_insert")) {
			AppUserLoginRecord appUserLoginRecord = (AppUserLoginRecord)data.getExecutionDO();
			appUserLoginRecordDao.insertUserLogin(appUserLoginRecord);
		}else if (data.getKey().equals("userRegistCode_insert")) {
			TjUserRegistCode tjUserRegistCode = (TjUserRegistCode)data.getExecutionDO();
			tjUserRegistCodeDao.insert(tjUserRegistCode);
		}else if (data.getKey().equals("bannerClick_insert")) {
			AppBannerClick bannerClick = (AppBannerClick)data.getExecutionDO();
			bannerClickDao.insert(bannerClick);
		}
		return true;
	}
	
	class Data<T>{
		
		Data(String key, T executionDO) {
			this.key = key;
			this.executionDO = executionDO;
		}
		
		@Getter
		@Setter
		private String key;

		@Getter
		@Setter
		private T executionDO;
	}

}





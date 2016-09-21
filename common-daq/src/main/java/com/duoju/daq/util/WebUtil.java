package com.duoju.daq.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.duoju.daq.model.ResponseModel;


/**
 *  处理json转map时出现空串报错的问题
 * @author ZhuZF
 * E-mail: zhuzhengfang13092@126.com
 * @date 创建时间：2015年10月14日 下午5:38:08 
 * @version 1.0 
 * @parameter 
 * @since  
 * @return  
 */
public class WebUtil {
	public static final String EMPTY_STRING = "";
	public static final String getParameter(Map<String,Object> param_map, String name,Object defalutKey) {
		if(null==param_map.get(name)||("").equals(param_map.get(name))){
			return defalutKey.toString();
		}
			return param_map.get(name).toString();
		}
	public static final String getParameter(Map<String,Object> param_map, String name) {
		String stringValue = EMPTY_STRING;
		if(null==param_map.get(name)||param_map.get(name).equals("")){
			return stringValue.toString();
		}
			return param_map.get(name).toString();
		}
		
		
		/**
		 * 返回数据结果带body
		 * @param object
		 * @param responseCode
		 * @param responseMsg
		 * @return
		 */
		public static String responseResult(Object object,ResponseModel responseModel){
			Map<String,Object> resultMap =new HashMap<String,Object>();
			resultMap.put("responseCode", responseModel.getResponseCode());
			resultMap.put("responseMsg", responseModel.getResponseMsg());
			resultMap.put("body", object);
			return FastJsonUtil.objectToJson(resultMap);
		}
		
		/**
		 * 返回数据结果不带body
		 * @param object
		 * @param responseCode
		 * @param responseMsg
		 * @return
		 */
		public static String responseResult(ResponseModel responseModel){
			Map<String,Object> resultMap =new HashMap<String,Object>();
			resultMap.put("responseCode", responseModel.getResponseCode());
			resultMap.put("responseMsg", responseModel.getResponseMsg());
			return FastJsonUtil.objectToJson(resultMap);
		}
		
		
		/**
		 * 验证请求json参数有误返回的数据
		 * @return
		 */
		public static String jsonErrorResult(){
			Map<String,Object> resultMap =new HashMap<String,Object>();
			resultMap.put("responseCode", EMUtil.RESPONS_STATUS.ERROR_CODE.KEY);
			resultMap.put("responseMsg", "请求参数有误");
			//resultMap.put("body", "");
			return FastJsonUtil.objectToJson(resultMap);
		}
		
		/**
		 * 验证必传参数null
		 * @param json
		 * @param objects
		 * @return
		 */
		public static boolean validateRequest(String json, String...strs ){
			//json为null
			if(null ==json || StringUtils.isBlank(json)){
				return false;
			}
			if(null !=strs){
				Map<String,Object> parameterMap = FastJsonUtil.jsonToObject(json, Map.class);
				for (String str : strs) {
					Object partyIdObj = parameterMap.get(str);
					if (partyIdObj == null) {
						return false;
					}
				}
			}
			
			return true;
		}
		
		
		/**
		 * 获取url传输的参数
		 * @param request
		 * @param name
		 * @return
		 */
		public static final String getParameter(HttpServletRequest request, String name) {
			String value = request.getParameter(name);
			String stringValue = EMPTY_STRING;
			if (value != null) {
				return value;
			}
			return stringValue;
		}
}

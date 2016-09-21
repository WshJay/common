package com.duoju.daq.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * 
 * 创建人: LCN
 * 创建时间：2015年10月16日
 * 类说明 :JSON工具类
 * 参考地址:
 * http://blog.csdn.net/wutongyu344/article/details/7321591
 * http://www.open-open.com/lib/view/open1405608707281.html
 * 
 * 主要的使用入口
	Fastjson API入口类是com.alibaba.fastjson.JSON，常用的序列化操作都可以在JSON类上的静态方法直接完成。
	public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray 
	public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject    
	public static final <T> T parseObject(String text, Class<T> clazz); // 把JSON文本parse为JavaBean 
	public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray 
	public static final <T> List<T> parseArray(String text, Class<T> clazz); //把JSON文本parse成JavaBean集合
	public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本 
	public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本 
	public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray。
	
	有关类库的一些说明
	SerializeWriter：相当于StringBuffer
	JSONArray：相当于List<Object>
	JSONObject：相当于Map<String, Object>
	JSON反序列化没有真正数组，本质类型都是List<Object>
	
  	Fastjson的SerializerFeature序列化属性
  	DisableCheckSpecialChar：一个对象的字符串属性中如果有特殊字符如双引号，将会在转成json时带有反斜杠转移符。如果不需要转义，可以使用这个属性。默认为false 
	QuoteFieldNames———-输出key时是否使用双引号,默认为true 
	WriteMapNullValue——–是否输出值为null的字段,默认为false 
	WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null 
	WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null 
	WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null 
	WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
	
	SerializeConfig 序列化的一些配置
	
 * 
 * */
public class FastJsonUtil {
	
	//序列化 日期格式
	private static SerializeConfig config = new SerializeConfig();  
	private static String dateFormat="yyyy-MM-dd HH:mm:ss";  
	static {  
	    config.put(Date.class, new SimpleDateFormatSerializer(dateFormat));  
	} 
	
	
	/**
	 * 对象转JSON字符窜 带自定义日期格式
	 * @param object
	 * @param formatDate
	 * @return
	 */
	public static String objectToJson(Object object,String formatDate){
		String json =FastJsonUtil.toJSONString(object, formatDate,
				SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullBooleanAsFalse,
				SerializerFeature.WriteNullStringAsEmpty);
		return json;
	}
	
	/**
	 * 对象转JSON字符窜 默认日期格式
	 * @param object
	 * @return
	 */
	public static String objectToJson(Object object){
		//%1$tF %1tT 格式化日期 String.format(formatDate, value);
		String json =FastJsonUtil.toJSONString(object, "%1$tF %1tT",
				SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullBooleanAsFalse,
				SerializerFeature.WriteNullStringAsEmpty);
		return json;
	}
	
	
	

	/**
	 * null转""
	 * @param object
	 * @param features
	 * @return
	 */
	public static String toJSONString(Object object, final String formatDate,
			SerializerFeature ...features) {
		SerializeWriter out = new SerializeWriter();
		String s;
		JSONSerializer serializer = new JSONSerializer(out);
		SerializerFeature arr$[] = features;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; i$++) {
			SerializerFeature feature = arr$[i$];
			serializer.config(feature, true);
		}

		serializer.getValueFilters().add(new ValueFilter() {
			public Object process(Object obj, String s, Object value) {
				if(null!=value) {
					//日期统一格式化
					if(value instanceof java.util.Date) {
						return String.format(formatDate, value);
					}
					return value;
				}else {
					return null;
					//return "";
				}
			}
		});
		serializer.write(object);
		s = out.toString();
		out.close();
		return s;
	}
	
	/**
	 * json转Object 泛型
	 * @param json
	 * @param clazz
	 * @return
	 */
    public static <T> T jsonToObject(String json, Class<T>  clazz) {
		 T t = JSON.parseObject(json,clazz);
		 return t;
    }
	 
	
	public static void main(String[] args) {
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("1", "11");
		map.put("2", "22");
		map.put("3", "33");
		map.put("4", new Date());
		map.put("5", null);
		String s =FastJsonUtil.objectToJson(map);
		System.out.println(s);
		
	}
}

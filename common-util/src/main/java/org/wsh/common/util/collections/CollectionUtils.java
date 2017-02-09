package org.wsh.common.util.collections;

import java.lang.reflect.Field;
import java.util.*;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 集合工具类
 * Project:     <common-util>
 * File Name:   <CollectionUtils.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2016年4月17日 下午7:29:51
 */
public class CollectionUtils {

	private final static Logger log = LoggerFactory.getLogger(CollectionUtils.class);
	/**
	 * LIST转MAP
	 * @param key 
	 * @param list
	 * @return
	 */
	public static <T> Map<String, T> ListCovertMap(String key, List<T> list) {
		Map<String,T> map = new HashMap<String,T>();
		for (T t : list) {
			try {
				Field field = t.getClass().getDeclaredField(key);
				field.setAccessible(true); // 设置些属性是可以访问的
				Object keyObj = field.get(t);
				if (keyObj != null) {
					map.put(keyObj.toString(), t);
				}
			} catch (NoSuchFieldException e) {
				log.error("List转Map异常",e);
			} catch (SecurityException e) {
				log.error("List转Map异常",e);
			} catch (IllegalArgumentException e) {
				log.error("List转Map异常",e);
			} catch (IllegalAccessException e) {
				log.error("List转Map异常",e);
			}
		}
		return map;
	}
	
	/**
	 * LIST转MAP
	 * @param key
	 * @param value
	 * @param list
	 * @return
	 */
	public static <T> Map<String, Integer> ListCovertMap(String key, String value, List<T> list) {
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		for (T t : list) {
			try {
				Field fieldKey = t.getClass().getDeclaredField(key);
				Field fieldValue = t.getClass().getDeclaredField(value);
				fieldKey.setAccessible(true); // 设置些属性是可以访问的
				fieldValue.setAccessible(true); // 设置些属性是可以访问的
				Object keyObj = fieldKey.get(t);
				Object valueObj = fieldValue.get(t);
				if (keyObj != null && valueObj != null) {
					map.put(keyObj.toString(), Integer.parseInt(valueObj.toString()));
				}
			} catch (NoSuchFieldException e) {
				log.error("List转Map异常",e);
			} catch (SecurityException e) {
				log.error("List转Map异常",e);
			} catch (IllegalArgumentException e) {
				log.error("List转Map异常",e);
			} catch (IllegalAccessException e) {
				log.error("List转Map异常",e);
			}
		}
		return map;
	}

	public static <T>HashSet<Long> ListCovertKeySet(String key, List<T> list) {
		HashSet<Long> set = new HashSet<>();
		for (T t : list) {
			try {
				Field field = t.getClass().getDeclaredField(key);
				field.setAccessible(true); // 设置些属性是可以访问的
				Object keyObj = field.get(t);
				if (keyObj != null) {
					set.add(Long.parseLong(keyObj.toString()));
				}
			} catch (NoSuchFieldException e) {
				log.error("List转SET异常",e);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
   			}
		}
		return set;
	}
}


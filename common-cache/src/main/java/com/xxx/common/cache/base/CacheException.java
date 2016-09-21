package com.xxx.common.cache.base;

/**
 * 缓存异常处理 
 * File Name: <CacheException.java> 
 * Comments: <对此类的描述，可以引用系统设计中的描述> 
 * JDK version used: <JDK1.6>
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-4-15 下午4:23:25
 */
public class CacheException extends RuntimeException {
	
	private static final long serialVersionUID = 7947189632939175201L;

	/**
	 * 创建一个CacheException
	 */
	public CacheException() {
		super();
	}

	public CacheException(String message) {
		super(message);
	}

	public CacheException(Throwable cause) {
		super(cause);
	}

	public CacheException(String message, Throwable cause) {
		super(message, cause);
	}

}

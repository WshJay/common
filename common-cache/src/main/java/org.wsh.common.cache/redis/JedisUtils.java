package org.wsh.common.cache.redis;

import org.wsh.common.cache.redis.mapper.ISetMapper;
import org.wsh.common.cache.redis.transcoder.ListTranscoder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  redis客户端分片方案
 * since Date： 2016/11/24 13:56
 */
public class JedisUtils {

	// 一些时间常量
	public static final Integer
	// 半小时的秒数
	SECOND_HALF_HOURS = 60 * 30,
	// 一小时的秒数
	SECOND_HOURS = 60 * 60,
	// 一天的秒数
	SECOND_DAY = 60 * 60 * 24,
	// 一周的秒数
	SECOND_WEEK = 60 * 60 * 24 * 7,
	// 一个月的秒数;
	SECOND_MONTH = SECOND_DAY * 30;

	protected ShardedJedisPool jedisPool;

	public void setJedisPool(ShardedJedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	/**
	 * 借出jedis连接实例
	 * 
	 * @return
	 */
	protected ShardedJedis borrow() {
		return jedisPool.getResource();
	}

	/**
	 * 用完了别忘了还回来
	 */
	protected void returnConn(ShardedJedis jedis) {
		jedisPool.returnResource(jedis);
	}

	// 从String到ISetMapper
	protected <T extends ISetMapper> List<T> stringToMapper(Jedis jedis, Set<String> set, Class<T> clazz) {
		List<T> tList = new ArrayList<T>(set.size());
		T t = null;
		for (String value : set) {
			try {
				t = clazz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			// t.mapper(jedis, value);
			tList.add(t);
		}
		return tList;
	}

	protected <T extends ISetMapper> List<T> stringToMapper(Jedis jedis, List<String> list, Class<T> clazz) {
		List<T> tList = new ArrayList<T>(list.size());
		T t = null;
		for (String value : list) {
			try {
				t = clazz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			// t.mapper(jedis, value);
			tList.add(t);
		}
		return tList;
	}

	// list操作
	/**
	 * 添加一个List
	 * 
	 * @param key
	 * @param list
	 */
	public void addList(String key, List<?> list, Integer seconds) {
		ShardedJedis jedis = borrow();
		jedis.set(key.getBytes(), ListTranscoder.serialize(list));
		if (seconds != null && seconds > 0) {
			jedis.expire(key, seconds);
		}
		returnConn(jedis);
	}

	/**
	 * 从缓存里获取一个List
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getList(String key, Class<T> clazz) {
		ShardedJedis jedis = borrow();
		byte[] in = jedis.get(key.getBytes());
		List<T> list = (List<T>) ListTranscoder.deserialize(in);
		returnConn(jedis);
		return list;
	}

	public Object doAction(IAction action) {
		ShardedJedis jedis = borrow();
		try {
			return action.action(jedis);
		} finally {
			returnConn(jedis);
		}
	}

	public Object doAction(Object args, IAction action) {
		ShardedJedis jedis = borrow();
		try {
			return action.action(jedis, args);
		} finally {
			returnConn(jedis);
		}
	}

	public static class IAction {
		public Object action(ShardedJedis jedis) {
			return null;
		}

		public Object action(ShardedJedis jedis, Object args) {
			return null;
		}
	}
}

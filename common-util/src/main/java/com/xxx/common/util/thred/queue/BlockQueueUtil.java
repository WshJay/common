package com.xxx.common.util.thred.queue;

import java.util.Iterator;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.Getter;
import lombok.Setter;

public class BlockQueueUtil {
	
	private static Logger log = LoggerFactory.getLogger(BlockQueueUtil.class);
	
	/**
	 * 缓存对象 map
	 */
	@SuppressWarnings("unchecked")
	public static CachePool<String, Object> mapPool = CachePool.getInstance();
	
	public void startThread(Executor threadPool) {
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
//		if (data.getKey().equals("update")) {
//			TjUserStay tjUserStay = (TjUserStay)data.getExecutionDO();
//			tjUserStayDao.updateStayLongById(tjUserStay.getId(), tjUserStay.getLeaveTime(), tjUserStay.getStayLong());
//		}else if (data.getKey().equals("insert")) {
//			TjUserStay tjUserStay = (TjUserStay)data.getExecutionDO();
//			tjUserStayDao.insert(tjUserStay);
//		}
		
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


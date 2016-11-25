package org.wsh.common.util.thread.pool;

import org.apache.commons.pool.PoolableObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  common-pool扩展的线程池工厂<兼容commons-pool 1.5.4的写法>
 * since Date： 2016/11/24 13:48
 */
public class ThreadPoolableFactory implements PoolableObjectFactory {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public ThreadObject makeObject() throws Exception {

		ThreadObject thread = new ThreadObject();
		thread.start();
		return thread;
	}

	@Override
	public void destroyObject(Object obj) throws Exception {
		ThreadObject thread = (ThreadObject) obj;
		thread.kill();
	}

	@Override
	public boolean validateObject(Object obj) {
		ThreadObject thread = (ThreadObject) obj;
		return thread.isBeing();
	}

	@Override
	public void activateObject(Object obj) throws Exception {
	}

	@Override
	public void passivateObject(Object obj) throws Exception {
		ThreadObject thread = (ThreadObject) obj;
		thread.setTask(null);
		synchronized (thread) {
			try {
				thread.notify();
			} catch (Exception e) {
				log.warn(e.getMessage(), e);
			}
		}

	}

}

package com.xxx.common.test.zookeeper.demo;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WatcherDemo implements Watcher{
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void process(WatchedEvent event) {
		// 删除
		if (event.getType() == EventType.NodeDeleted) {
			System.out.println("node delete");
		}
		// 子节点改变
		if (event.getType() == EventType.NodeChildrenChanged) {
			System.out.println("node ChildrenChanged");
		}
		// 子节点改变
		if (event.getType() == EventType.NodeCreated) {
			System.out.println("node Created");
		}
		if (event.getType() == EventType.NodeDataChanged) {
			System.out.println("node DataChanged");
		}
	}

}


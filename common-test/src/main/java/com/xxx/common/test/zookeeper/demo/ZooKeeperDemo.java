package com.xxx.common.test.zookeeper.demo;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ZooKeeper Demo
 * Project:     <common-test>
 * File Name:   <ZooKeeperDemo.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2016年5月6日 下午2:19:38
 */
public class ZooKeeperDemo {
	
	private final static Logger log = LoggerFactory.getLogger(ZooKeeperDemo.class);

	public static void main(String args[]) throws IOException, KeeperException,
			InterruptedException {
 
	 	ZooKeeper zk = new ZooKeeper("121.40.111.195:2181", 300000,null);
	 	// 判断节点是否存在
	 	Stat stat = zk.exists("/root/zooData", true);
	 	if (stat == null) {
	 		System.out.println("----Create Stat Success -----");
	 		 // 创建一个目录节点
	        zk.create("/root/zooData", "testData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
	                CreateMode.PERSISTENT);
		}
	 	// 判断子节点是否存在
	 	Stat childStat = zk.exists("/root/zooData/childOne", true);
	 	if (childStat == null) {
	 		System.out.println("----Create Child Stat Success -----");
	 		 // 创建一个目录节点
	        zk.create("/root/zooData/childOne", "testData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
	                CreateMode.PERSISTENT);
		}
	 	zk.setData("/root/zooData", "Hello".getBytes(), -1);
	 	System.out.println(new String(zk.getData("/root/zooData", true, stat)));
        // 取出子目录节点列表
        System.out.println(zk.getChildren("/root/zooData", true));
        // 修改子目录节点数据
        zk.setData("/root/zooData/childOne", "modifyChildDataOne".getBytes(), -1);
        System.out.println("目录节点状态：[" + zk.exists("/root/zooData", true) + "]");
        // 判断子节点是否存在
	 	Stat childTwoStat = zk.exists("/root/zooData/childTwo", true);
	 	if (childTwoStat == null) {
	 		System.out.println("----Create Child Two Stat Success -----");
	 		 // 创建一个目录节点
	        zk.create("/root/zooData/childTwo", "testData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
	                CreateMode.PERSISTENT);
		}
        System.out.println(new String(zk.getData("/root/zooData/childTwo", true, childTwoStat)));
        // 删除子目录节点
        zk.delete("/root/zooData/childTwo", -1);
        zk.delete("/root/zooData/childOne", -1);
        // 删除父目录节点
        zk.delete("/root/zooData", -1);
        // 关闭连接
        zk.close();
    }
}


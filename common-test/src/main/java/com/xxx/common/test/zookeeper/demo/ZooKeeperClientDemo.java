package com.xxx.common.test.zookeeper.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.zkclient.IZkChildListener;
import com.github.zkclient.IZkDataListener;
import com.github.zkclient.IZkStateListener;
import com.github.zkclient.ZkClient;

public class ZooKeeperClientDemo {
	
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	String PATH = "/root/configcenter";
	
	List<String> serverList = new ArrayList<String>();
	
	@Test
	public void test(){
		
		String serviceName = "service-A";
		String zkServer = "121.40.111.195:2181";
		String SERVICE_PATH = PATH + "/" + serviceName;
		ZkClient zkClient = new ZkClient(zkServer);
		boolean rootExists  = zkClient.exists(PATH);
		if (!rootExists) {
			// 创建服务节点
			zkClient.createPersistent(PATH);
			log.info("Create Root Stat Success...");
		}
		boolean serverExists  = zkClient.exists(SERVICE_PATH);
		if (!serverExists) {
			// 创建服务节点
			zkClient.createPersistent(SERVICE_PATH);
			log.info("Create Service Stat Success...");
		}
		try {
			InetAddress addr = InetAddress.getLocalHost();
			// 获取本机IP
//			String ip = addr.getHostAddress().toString();
			String ip = "121.40.111.195";
			// 创建当前服务节点
			zkClient.createEphemeral(SERVICE_PATH + "/" + ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		serverExists  = zkClient.exists(SERVICE_PATH);
		if (serverExists) {// 服务存在，取地址列表
			List<String> childServiceList = zkClient.getChildren(SERVICE_PATH);
			log.info(childServiceList.toString());
		}else{
			throw new RuntimeException("service not exists");
		}
		
		// 订阅子节点的变化
		zkClient.subscribeChildChanges(SERVICE_PATH, new IZkChildListener() {
			@Override
			public void handleChildChange(String parentPath, List<String> currentChilds)
					throws Exception {
				serverList = currentChilds;
			}
		});
		
		// 订阅数据的变化
		zkClient.subscribeDataChanges(SERVICE_PATH, new IZkDataListener() {

			@Override
			public void handleDataChange(String dataPath, byte[] data)
					throws Exception {
				
			}
			
			@Override
			public void handleDataDeleted(String dataPath)
					throws Exception {
			}

		});
		
		// 订阅节点连接状态的变化
		zkClient.subscribeStateChanges(new IZkStateListener() {

			@Override
			public void handleStateChanged(KeeperState state) throws Exception {
				
			}

			@Override
			public void handleNewSession() throws Exception {
				
			}
		});
	}

	
	
}


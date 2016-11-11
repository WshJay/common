package org.wsh.common.enums.basic;

import static com.sun.jmx.snmp.daemon.CommunicatorServer.OFFLINE;
import static com.sun.jmx.snmp.daemon.CommunicatorServer.ONLINE;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  在线状态
 * since Date： 2016-11-10 17:07
 */
public enum OnlineStatus {

    /**
     * 在线
     */
    ONLINE,

    /**
     * 已下线
     */
    OFFLINE;
}

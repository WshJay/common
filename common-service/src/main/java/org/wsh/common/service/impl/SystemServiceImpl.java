package org.wsh.common.service.impl;

import org.springframework.stereotype.Service;
import org.wsh.common.enums.expection.Errors;
import org.wsh.common.model.system.SystemDO;
import org.wsh.common.service.api.SystemService;
import org.wsh.common.support.response.ResponseDO;
import org.wsh.common.util.logger.LoggerService;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  获取系统信息
 * since Date： 2017-02-07 13:35
 */
@Service("systemService")
public class SystemServiceImpl extends LoggerService implements SystemService{


    @Override
    public ResponseDO<SystemDO> getSystemDO() {

        try {
            String os = System.getProperty("os.name");
            String javaVersion = System.getProperty("java.version");
            double freeMemory = Math.round((double) Runtime.getRuntime().freeMemory()/ 1024 / 1024);
            double totalMemory = Math.round((double) Runtime.getRuntime().totalMemory()/ 1024 / 1024);
            double maxMemory = Math.round((double) Runtime.getRuntime().maxMemory()/ 1024 / 1024);
            double usedMemory = totalMemory - freeMemory;
//            double memPercent = Math.round(freeMemory / totalMemory * 100) ;
            SystemDO systemDO = new SystemDO(os,javaVersion,maxMemory, totalMemory,usedMemory,freeMemory);
            return new ResponseDO<>(systemDO);
        } catch (Exception e) {
            logger.error("获取系统信息异常!",e);
            return new ResponseDO<>("-1","获取系统信息异常!");
        }
    }
}

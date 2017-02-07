package org.wsh.common.service.api;

import org.springframework.stereotype.Service;
import org.wsh.common.model.system.SystemDO;
import org.wsh.common.support.response.ResponseDO;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  获取系统信息
 * since Date： 2017-02-07 13:36
 */
public interface SystemService {

    /**
     * 获取运行系统信息
     * @return ResponseDO<SystemDO>
     */
    public ResponseDO<SystemDO> getSystemDO();
}

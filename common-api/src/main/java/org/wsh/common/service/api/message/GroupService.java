package org.wsh.common.service.api.message;

import org.wsh.common.model.msg.GroupDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;

import java.util.List;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-11-07 14:09
 */
public interface GroupService {

    /**
     * 多条件查询组信息
     * @param groupDO GroupDO
     * @param pagination Pagination
     * @return OptionsResponseDO
     */
    public OptionsResponseDO<List<GroupDO>> getGroupListByParams(GroupDO groupDO, Pagination pagination) throws BusinessException;

    /**
     * 新增组
     * @param groupDO GroupDO
     * @return ResponseDO
     * @throws BusinessException
     */
    public ResponseDO doAddGroup(GroupDO groupDO) throws BusinessException;
}

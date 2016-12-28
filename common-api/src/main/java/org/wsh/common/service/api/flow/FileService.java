package org.wsh.common.service.api.flow;

import org.wsh.common.model.flow.FileDO;
import org.springframework.stereotype.Service;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;
import static org.wsh.common.support.beans.BeansUtil.newOptionsResponseDO;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  File服务层
* since Date： 2016-12-27 15:31:18
*/
@Service
public interface FileService {

	/**
	* 多条件查询(分页)
	* @param fileDO FileDO
	* @param pagination  Pagination
	* @return OptionsResponseDO<List<FileDO>>
    */
    public OptionsResponseDO<List<FileDO>> queryFileDOListForPage(FileDO fileDO, Pagination pagination) throws BusinessException;

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<FileDO>
    */
    public ResponseDO<FileDO> getFileDOById(Long id) throws BusinessException;

    /**
    * 添加
    * @param fileDO FileDO
    * @return ResponseDO<FileDO>
    */
    public ResponseDO<FileDO> addFileDO(FileDO fileDO) throws BusinessException;

    /**
    * 修改
    * @param fileDO FileDO
	* @return ResponseDO<FileDO>
    */
    public ResponseDO<FileDO> modifyFileDO(FileDO fileDO) throws BusinessException;

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<FileDO>
    * @throws BusinessException
    */
    public ResponseDO<FileDO> delFileDO(Long id) throws BusinessException;
}

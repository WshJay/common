package org.wsh.common.service.api.message;

import org.springframework.stereotype.Service;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;

import static javafx.scene.input.KeyCode.L;
import static org.wsh.common.support.beans.BeansUtil.newOptionsResponseDO;
import java.util.List;
import java.util.Map;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  UserBasic服务层
* since Date： 2016-11-23 17:00:43
*/
@Service
public interface UserBasicService {

	/**
	* 多条件查询(分页)
	* @param userBasicDO UserBasicDO
	* @param pagination  Pagination
	* @return OptionsResponseDO<List<UserBasicDO>>
    */
    public OptionsResponseDO<List<UserBasicDO>> queryUserBasicDOListForPage(UserBasicDO userBasicDO, Pagination pagination) throws BusinessException;

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<UserBasicDO>
    */
    public ResponseDO<UserBasicDO> getUserBasicDOById(Long id) throws BusinessException;

    /**
     * 根据用户名称查询
     * @param userName String
     * @return ResponseDO<UserBasicDO>
     */
    public ResponseDO<UserBasicDO> getUserBasicDOByUserName(String userName) throws BusinessException;

    /**
    * 添加
    * @param userBasicDO UserBasicDO
    * @return ResponseDO<UserBasicDO>
    */
    public ResponseDO<UserBasicDO> addUserBasicDO(UserBasicDO userBasicDO) throws BusinessException;

    /**
    * 修改
    * @param userBasicDO UserBasicDO
	* @return ResponseDO<UserBasicDO>
    */
    public ResponseDO<UserBasicDO> modifyUserBasicDO(UserBasicDO userBasicDO) throws BusinessException;

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<UserBasicDO>
    * @throws BusinessException
    */
    public ResponseDO<UserBasicDO> delUserBasicDO(Long id) throws BusinessException;

    /**
     * 根据Ids获取用户名
     * @param idList List<List<Long>>
     * @return ResponseDO<Map<Long,String>>
     */
    public ResponseDO<Map<Long,String>> queryUserListByIds(List<Long> idList);
}

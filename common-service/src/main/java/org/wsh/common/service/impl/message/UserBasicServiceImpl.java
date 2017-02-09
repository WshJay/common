package org.wsh.common.service.impl.message ;

import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.Caching;
import org.wsh.common.dao.message.UserBasicDao;
import org.wsh.common.interceptor.MapParam;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.service.api.message.UserBasicService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.Assert;
import org.springframework.stereotype.Service;
import org.wsh.common.util.logger.LoggerService;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;
import javax.annotation.Resource;
import static org.wsh.common.support.beans.BeansUtil.newOptionsResponseDO;
import static org.wsh.common.support.beans.BeansUtil.newStaticOptionsResponseDO;
import static org.wsh.common.support.beans.BeansUtil.newStaticResponseDO;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  UserBasic服务实现层
* since Date： 2016-11-23 17:00:43
*/
@Service("userBasicService")
public class UserBasicServiceImpl extends LoggerService implements UserBasicService{

    @Resource
    private UserBasicDao userBasicDao;

	/**
	* 多条件查询(分页)
	* @param userBasicDO UserBasicDO
	* @param pagination  Pagination
	* @return List<UserBasicDO>
    */
    @Override
    public OptionsResponseDO<List<UserBasicDO>> queryUserBasicDOListForPage(UserBasicDO userBasicDO, Pagination pagination) throws BusinessException{
        try {
            int totalCount = userBasicDao.selectCountByParams(userBasicDO);
            if (totalCount <= 0) {
                return newStaticOptionsResponseDO(new ArrayList<UserBasicDO>());
            }
            pagination.setTotalCount(totalCount);
            List<UserBasicDO> userBasicDOList = userBasicDao.selectListByParams(userBasicDO, pagination.getRowBounds());
            return newOptionsResponseDO(totalCount, userBasicDOList, pagination.getPageSize(),pagination.getPP());
        } catch (Exception e) {
            throw new BusinessException("多条件查询UserBasicDO信息异常",e);
        }
    }

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<UserBasicDO>
    */
    @Override
    @Cacheable(value = "common:userBasicDO",key = "'common:userBasicDO:id:' + #id")
    public ResponseDO<UserBasicDO> getUserBasicDOById(Long id) throws BusinessException{
        try {
            Assert.isTrue(id != null,"查询Id不能为空!");
            UserBasicDO userBasicDO = userBasicDao.selectById(id);
            return newStaticResponseDO(userBasicDO);
        } catch (Exception e) {
            throw new BusinessException("根据ID=>[" + id + "]查询UserBasicDO信息异常",e);
        }
    }

    @Override
    @Cacheable(value = "common:userBasicDO",key = "'common:userBasicDO:userName:' + #userName")
    public ResponseDO<UserBasicDO> getUserBasicDOByUserName(String userName) throws BusinessException {
        try {
            Assert.isTrue(StringUtils.isNotBlank(userName),"查询用户名不能为空!");
            UserBasicDO userBasicDO = userBasicDao.selectById(1L);
            return newStaticResponseDO(userBasicDO);
        } catch (Exception e) {
            throw new BusinessException("根据userName=>[" + userName + "]查询UserBasicDO信息异常",e);
        }
    }

    /**
    * 添加
    * @param userBasicDO UserBasicDO
    * @return ResponseDO<UserBasicDO>
    */
    @Override
    @CachePut(value = "common:userBasicDO",key = "'common:userBasicDO:id:' + #userBasicDO.id")
    public ResponseDO<UserBasicDO> addUserBasicDO(UserBasicDO userBasicDO) throws BusinessException{
        try {
            // Validate
            validateForAdd(userBasicDO);

            // Insert
            int result = userBasicDao.insert(userBasicDO);
            if (result < 1) {
                throw new Exception("sql插入数据为0,请检查各项参数!");
            }
            logger.info("新增ID=>[" + userBasicDO.getId() + "]的UserBasicDO成功");
            return newStaticResponseDO(userBasicDO);
        } catch (Exception e) {
            throw new BusinessException("新增ID=>[" + userBasicDO.getId() + "]的UserBasicDO信息异常",e);
        }
    }

    /**
    * Validate Add
    * @param userBasicDO UserBasicDO
    */
    private void validateForAdd(UserBasicDO userBasicDO) {
        Assert.isTrue(userBasicDO != null,"userBasicDO不能为空!");
        // TODO Validate
    }

    /**
    * 修改
    * @param userBasicDO UserBasicDO
	* @return ResponseDO<UserBasicDO>
    */
    @Override
    @Caching(evict={@CacheEvict(value="common:userBasicDO",key="'common:userBasicDO:id:' + #userBasicDO.id"),@CacheEvict(value="common:userBasicDO",key="'common:userBasicDO:userName:' + #userBasicDO.userName")})
    public ResponseDO<UserBasicDO> modifyUserBasicDO(UserBasicDO userBasicDO) throws BusinessException{
        try {

            // validate
            UserBasicDO oldUserBasicDO = validateForUpdate(userBasicDO);

            // Update
            userBasicDO.setVersion(oldUserBasicDO.getVersion());
            int result = userBasicDao.updateById(userBasicDO);
            if (result < 1) {
                throw new Exception("sql修改数据为0,请检查各项参数!");
            }
            logger.info("修改ID=>[" + userBasicDO.getId() + "]的userBasicDO成功!");
            return newStaticResponseDO(userBasicDO);
        }catch (Exception e){
            logger.error("修改ID=>[" + userBasicDO.getId() + "]的userBasicDO异常!");
            throw new BusinessException("修改ID=>[" + userBasicDO.getId() +"]的UserBasicDO信息异常",e);
        }
    }

    /**
    * Validate Update
    * @param userBasicDO UserBasicDO
    * @return UserBasicDO
    */
    private UserBasicDO validateForUpdate(UserBasicDO userBasicDO) {

        Assert.isTrue(userBasicDO != null,"userBasicDO不能为空!");
        Assert.isTrue(userBasicDO.getId() != null,"ID不能为空!");
        // TODO Validate
        UserBasicDO oldUserBasicDO = userBasicDao.selectById(userBasicDO.getId());
        Assert.isTrue(oldUserBasicDO != null,"查询不到ID=>[" + userBasicDO.getId() + "]的信息!");
        return oldUserBasicDO;
    }

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<UserBasicDO>
    * @throws BusinessException
    */
    @Override
    @CacheEvict(value = "common:userBasicDO",key = "'common:userBasicDO:id:' + #id",beforeInvocation = true)
    public ResponseDO<UserBasicDO> delUserBasicDO(Long id) throws BusinessException{
        try {
            // validate
            Assert.isTrue(id != null,"ID不能为空!");

            UserBasicDO olduserBasicDO = userBasicDao.selectById(id);
            Assert.isTrue(olduserBasicDO != null,"查询不到ID=>" + id + "的信息!");
            UserBasicDO userBasicDO = new UserBasicDO();
            userBasicDO.setId(id);
            userBasicDO.setIsDeleted(1);
            userBasicDO.setVersion(olduserBasicDO.getVersion());
            // update
            int result = userBasicDao.updateIsDeleteById(userBasicDO);
            if (result < 1) {
                throw new Exception("数据已删除,请勿重复操作!");
            }
            logger.info("删除ID=>[" + id + "]的userBasicDO成功!");
            return newStaticResponseDO(userBasicDO);
        }catch (Exception e){
            logger.error("删除ID=>[" + id + "]的userBasicDO异常!");
            throw new BusinessException("删除ID=>[" + id +"]的UserBasicDO异常",e);
        }
    }

    @Override
    public ResponseDO<Map<Long, String>> queryUserListByIds(List<Long> idList) {
        try {
            // validate
            Assert.notEmpty(idList,"Ids不能为空!");

            Map<Long,String> map = userBasicDao.selectUserNameByIds(new MapParam("id", "user_name",idList));
            return newStaticResponseDO(map);
        }catch (Exception e){
            logger.error("查询Ids=>[" + idList.toString() + "]的userName异常!");
            return new ResponseDO<>("-1","查询异常!");
        }
    }
}

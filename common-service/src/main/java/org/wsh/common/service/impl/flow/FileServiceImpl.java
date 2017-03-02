package org.wsh.common.service.impl.flow;

import org.wsh.common.cache.service.RedisService;
import org.wsh.common.model.flow.FileDO;
import org.wsh.common.dao.flow.FileDao;
import org.wsh.common.service.api.flow.FileService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.Assert;
import org.springframework.stereotype.Service;
import org.wsh.common.util.logger.LoggerService;
import org.springframework.transaction.annotation.Transactional;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;
import javax.annotation.Resource;
import static org.wsh.common.support.beans.BeansUtil.newOptionsResponseDO;
import static org.wsh.common.support.beans.BeansUtil.newStaticOptionsResponseDO;
import static org.wsh.common.support.beans.BeansUtil.newStaticResponseDO;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  File服务实现层
* since Date： 2016-12-27 15:31:18
*/
@Service("fileService")
public class FileServiceImpl extends LoggerService implements FileService{

    @Resource
    private FileDao fileDao;

    @Resource
    private RedisService redisService;

	/**
	* 多条件查询(分页)
	* @param fileDO FileDO
	* @param pagination  Pagination
	* @return List<FileDO>
    */
    @Override
    @Cacheable(value = "common:fileList",key = "'common:fileList:userId:' + #fileDO.userId")
    public OptionsResponseDO<List<FileDO>> queryFileDOListForPage(FileDO fileDO, Pagination pagination) throws BusinessException{
        try {
            int totalCount = fileDao.selectCountByParams(fileDO);
            if (totalCount <= 0) {
                return newStaticOptionsResponseDO(new ArrayList<FileDO>());
            }
            pagination.setTotalCount(totalCount);
            List<FileDO> fileDOList = fileDao.selectListByParams(fileDO, pagination.getRowBounds());
            return newOptionsResponseDO(totalCount, fileDOList, pagination.getPageSize(),pagination.getPP());
        } catch (Exception e) {
            throw new BusinessException("多条件查询FileDO信息异常",e);
        }
    }

    /**
    * 根据ID查询
    * @param id Long
    * @return ResponseDO<FileDO>
    */
    @Override
    @Cacheable(value = "common:fileDO",key = "'common:fileDO:id:' + #id")
    public ResponseDO<FileDO> getFileDOById(Long id) throws BusinessException{
        try {
            Assert.isTrue(id != null,"查询Id不能为空!");
            FileDO fileDO = fileDao.selectById(id);
            return newStaticResponseDO(fileDO);
        } catch (Exception e) {
            throw new BusinessException("根据ID=>[" + id + "]查询FileDO信息异常",e);
        }
    }

    /**
    * 添加
    * @param fileDO FileDO
    * @return ResponseDO<FileDO>
    */
    @Override
    @CachePut(value = "common:fileDO",key = "'common:fileDO:id:' + #fileDO.id")
    public ResponseDO<FileDO> addFileDO(FileDO fileDO) throws BusinessException{
        try {
            // Validate
            validateForAdd(fileDO);

            // Insert
            int result = fileDao.insert(fileDO);
            if (result < 1) {
                throw new Exception("sql插入数据为0,请检查各项参数!");
            }
            // 重新加载用户缓存
            redisService.getJedis().del("common:fileList:userId:" + fileDO.getUserId());
            logger.info("新增ID=>[" + fileDO.getId() + "]的FileDO成功");
            return newStaticResponseDO(fileDO);
        } catch (Exception e) {
            throw new BusinessException("新增ID=>[" + fileDO.getId() + "]的FileDO信息异常",e);
        }
    }

    /**
    * Validate Add
    * @param fileDO FileDO
    */
    private void validateForAdd(FileDO fileDO) {
        Assert.isTrue(fileDO != null,"fileDO不能为空!");
        // TODO Validate
    }

    /**
    * 修改
    * @param fileDO FileDO
	* @return ResponseDO<FileDO>
    */
    @Override
    @CacheEvict(value = "common:fileDO",key = "'common:fileDO:id:' + #fileDO.id",beforeInvocation = true)
    public ResponseDO<FileDO> modifyFileDO(FileDO fileDO) throws BusinessException{
        try {

            // validate
            FileDO oldFileDO = validateForUpdate(fileDO);

            // Update
            int result = fileDao.updateById(fileDO);
            if (result < 1) {
                throw new Exception("sql修改数据为0,请检查各项参数!");
            }
            // 重新加载用户缓存
            redisService.getJedis().del("common:fileList:userId:" + fileDO.getUserId());
            logger.info("修改ID=>[" + fileDO.getId() + "]的fileDO成功!");
            return newStaticResponseDO(fileDO);
        }catch (Exception e){
            logger.error("修改ID=>[" + fileDO.getId() + "]的fileDO异常!");
            throw new BusinessException("修改ID=>[" + fileDO.getId() +"]的FileDO信息异常",e);
        }
    }

    /**
    * Validate Update
    * @param fileDO FileDO
    * @return FileDO
    */
    private FileDO validateForUpdate(FileDO fileDO) {

        Assert.notNull(fileDO,"fileDO不能为空!");
        Assert.notNull(fileDO.getId(),"查询Id不能为空!");
        // TODO Validate
        FileDO oldFileDO = fileDao.selectById(fileDO.getId());
        Assert.notNull(oldFileDO,"查询不到ID=>[" + fileDO.getId() + "]的信息!");
        return oldFileDO;
    }

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<FileDO>
    * @throws BusinessException
    */
    @Override
    @CacheEvict(value = "common:fileDO",key = "'common:fileDO:id:' + #id",beforeInvocation = true)
    public ResponseDO<FileDO> delFileDO(Long id) throws BusinessException{
        try {
            // validate
            Assert.notNull(id,"查询Id不能为空!");
            FileDO oldfileDO = fileDao.selectById(id);
            Assert.notNull(oldfileDO,"查询不到ID=>" + id + "的信息!");
            FileDO fileDO = new FileDO();
            fileDO.setId(id);
            fileDO.setIsDeleted(1);
            // update
            int result = fileDao.updateIsDeleteById(fileDO);
            if (result < 1) {
                throw new Exception("数据已删除,请勿重复操作!");
            }
            // 重新加载用户缓存
            redisService.getJedis().del("common:fileList:userId:" + fileDO.getUserId());
            logger.info("删除ID=>[" + id + "]的fileDO成功!");
            return newStaticResponseDO(fileDO);
        }catch (Exception e){
            logger.error("删除ID=>[" + id + "]的fileDO异常!");
            throw new BusinessException("删除ID=>[" + id +"]的FileDO异常",e);
        }
    }
}

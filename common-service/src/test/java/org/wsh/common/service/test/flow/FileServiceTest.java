package org.wsh.common.service.test.flow;

import org.springframework.transaction.annotation.Transactional;
import org.wsh.common.model.flow.FileDO;
import org.wsh.common.pager.pagination.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wsh.common.service.api.flow.FileService;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.response.ResponseDO;
import org.wsh.common.util.logger.LoggerService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  服务接口单元测试
* since Date： 2017-03-02 11:56:40
*/
//@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:common-bean.xml")
public class FileServiceTest extends LoggerService{

    @Resource
    private FileService fileService;

    private Long id = 1L;

    @Test
    public void add() {
        try {
            FileDO fileDO = insert();
            id = fileDO.getId();
            logger.info(fileDO.toString());
        } catch (Exception e) {
            logger.error("添加数据异常!", e);
        }
    }

    private FileDO insert() throws Exception {
        FileDO fileDO = new FileDO();

        ResponseDO<FileDO> responseDO = fileService.addFileDO(fileDO);
        logger.info("Result:" + responseDO.isSuccess() + "ErrorCode:" + responseDO.getErrorCode() + "ErrorMsg:" + responseDO.getErrorMsg());
        return fileDO;
    }

    @Test
    public void selectListForPage() {
        try {
            FileDO fileDO = new FileDO();
            fileDO.setUserId(2L);
            OptionsResponseDO<List<FileDO>> responseDO = fileService.queryFileDOListForPage(fileDO, new Pagination(100));
            if (responseDO.isSuccess()) {
                for (FileDO fileDO1 : responseDO.getData()) {
                    logger.info(fileDO1.toString());
                }
            } else {
                logger.info("分页查询列表为空!");
            }
        } catch (Exception e) {
            logger.error("分页查询列表数据异常!", e);
        }
    }

    @Test
    public void selectById() {
        try {
            ResponseDO<FileDO> responseDO = fileService.getFileDOById(id);
            if (responseDO.isSuccess()) {
                logger.info(responseDO.getData().toString());
            }
        } catch (Exception e) {
            logger.error("根据ID查询表数据异常!", e);
        }
    }

    @Test
    public void updateById() {
        try {
            ResponseDO<FileDO> oldResponseDO = fileService.getFileDOById(id);
            FileDO fileDO = oldResponseDO.getData();
            fileDO.setName("ABC");
            ResponseDO<FileDO> responseDO = fileService.modifyFileDO(fileDO);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID更新表数据异常!", e);
        }
    }

    @Test
    public void delete() {
        try {
            FileDO fileDO = new FileDO();
            fileDO.setId(id);
            ResponseDO<FileDO> responseDO = fileService.delFileDO(id);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID逻辑删除表数据异常!", e);
        }
    }
}
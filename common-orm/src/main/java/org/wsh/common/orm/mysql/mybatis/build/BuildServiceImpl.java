package org.wsh.common.orm.mysql.mybatis.build;

import org.wsh.common.orm.mysql.mybatis.base.AbstractBuildFactory;
import org.wsh.common.orm.mysql.mybatis.bean.Table;
import org.wsh.common.orm.mysql.mybatis.bean.TableWapper;
import org.wsh.common.orm.mysql.mybatis.enums.OutPathKey;
import org.wsh.common.orm.mysql.mybatis.util.Util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.wsh.common.orm.mysql.mybatis.util.Util.parseDate;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  构建服务实现
 * since Date： 2016-11-16 15:21
 */
public class BuildServiceImpl extends AbstractBuildFactory {

    @Override
    public OutPathKey getOutPath() {
        return OutPathKey.SERVICE_IMPL;
    }

    @Override
    public void buildTable(TableWapper tableWapper) {
        String daoPackage = tableWapper.getDaoPackage();
        String pojoPackage = tableWapper.getPojoPackage();
        String servicePackage = tableWapper.getServicePackage();
        String serviceImplPackage = tableWapper.getServiceImplPackage();

        String output = tableWapper.getOutPathMap().get(getOutPath());
        Table table = tableWapper.getTable();
        String tableName = table.getName();
        String headName = Util.getUpperHumpName(tableName);
        String bigDoName = Util.getUpperHumpName(tableName)+"DO";
        String minDoName = Util.getHumpName(tableName)+"DO";
        String bigDaoName = Util.getUpperHumpName(tableName)+"Dao";
        String minDaoName = Util.getHumpName(tableName)+"Dao";
        String bigServiceName = Util.getUpperHumpName(tableName)+"Service";
        String minServiceName = Util.getHumpName(tableName)+"Service";
        String bigServiceImplName = Util.getUpperHumpName(tableName)+"ServiceImpl";
        String minServiceImplName = Util.getHumpName(tableName)+"ServiceImpl";

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pojoPackage", pojoPackage);
        map.put("daoPackage", daoPackage);
        map.put("servicePackage", servicePackage);
        map.put("serviceImplPackage", serviceImplPackage);
        map.put("bigDoName", bigDoName);
        map.put("minDoName", minDoName);
        map.put("bigDaoName", bigDaoName);
        map.put("minDaoName", minDaoName);
        map.put("bigServiceName", bigServiceName);
        map.put("minServiceName", minServiceName);
        map.put("bigServiceImplName", bigServiceImplName);
        map.put("minServiceImplName", minServiceImplName);
        map.put("headName", headName);
        map.put("date", parseDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        Util.writeCode("serviceImpl", map,output+bigServiceImplName+".java/");
    }
}

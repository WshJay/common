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
 * comments:  构件服务
 * since Date： 2016-11-16 15:21
 */
public class BuildService extends AbstractBuildFactory{

    @Override
    public OutPathKey getOutPath() {
        return OutPathKey.SERVICE;
    }

    @Override
    public void buildTable(TableWapper tableWapper) {

        String daoPackage = tableWapper.getDaoPackage();
        String pojoPackage = tableWapper.getPojoPackage();
        String servicePackage = tableWapper.getServicePackage();

        String output = tableWapper.getOutPathMap().get(getOutPath());
        Table table = tableWapper.getTable();
        String tableName = table.getName();
        String headName = Util.getUpperHumpName(tableName);
        String minDoName = Util.getHumpName(tableName)+"DO";
        String bigDoName = Util.getUpperHumpName(tableName)+"DO";
        String bigDaoName = Util.getUpperHumpName(tableName)+"Dao";
        String minDaoName = Util.getHumpName(tableName)+"Dao";

        String bigServiceName = Util.getUpperHumpName(tableName)+"Service";

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pojoPackage", pojoPackage);
        map.put("daoPackage", daoPackage);
        map.put("servicePackage", servicePackage);
        map.put("bigDoName", bigDoName);
        map.put("minDoName", minDoName);
        map.put("bigDaoName", bigDaoName);
        map.put("minDaoName", minDaoName);
        map.put("bigServiceName", bigServiceName);
        map.put("headName", headName);
        map.put("date", parseDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        Util.writeCode("service", map,output+bigServiceName+".java/");
    }
}

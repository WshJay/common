package org.wsh.common.orm.mysql.mybatis.build;

import org.wsh.common.orm.mysql.mybatis.base.AbstractBuildFactory;
import org.wsh.common.orm.mysql.mybatis.bean.Column;
import org.wsh.common.orm.mysql.mybatis.bean.Table;
import org.wsh.common.orm.mysql.mybatis.bean.TableWapper;
import org.wsh.common.orm.mysql.mybatis.enums.OutPathKey;
import org.wsh.common.orm.mysql.mybatis.util.Util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.wsh.common.orm.mysql.mybatis.util.Util.parseDate;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-12-27 17:01
 */
public class BuildServiceTest extends AbstractBuildFactory {

    @Override
    public OutPathKey getOutPath() {
        return OutPathKey.SERVICE_TEST;
    }

    @Override
    public void buildTable(TableWapper tableWapper) {
        String daoPackage = tableWapper.getDaoPackage();
        String pojoPackage = tableWapper.getPojoPackage();
        String servicePackage = tableWapper.getServicePackage();
        String serviceImplPackage = tableWapper.getServiceImplPackage();

        String outPath = tableWapper.getOutPathMap().get(OutPathKey.DEFULT);
        if(tableWapper.getOutPathMap().get(getOutPath()) != null){
            outPath = tableWapper.getOutPathMap().get(getOutPath());
        }
        Table table = tableWapper.getTable();
        String tableName = table.getName();
        String bigDoName = Util.getUpperHumpName(tableName)+"DO";
        String minDoName = Util.getHumpName(tableName)+"DO";
        String bigServiceName = Util.getUpperHumpName(tableName)+"Service";
        String minServiceName = Util.getHumpName(tableName)+"Service";
        String bigServiceTestName = bigServiceName+"Test";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("servicePackage", servicePackage);
        map.put("bigDoName", bigDoName);
        map.put("minDoName", minDoName);
        map.put("bigServiceName", bigServiceName);
        map.put("minServiceName", minServiceName);
        map.put("date", parseDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        Util.writeCode("serviceTest", map,outPath+bigServiceTestName+".java/");
    }
}

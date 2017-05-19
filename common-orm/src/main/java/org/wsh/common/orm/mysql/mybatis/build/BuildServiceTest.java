package org.wsh.common.orm.mysql.mybatis.build;

import org.wsh.common.orm.mysql.mybatis.base.AbstractBuildFactory;
import org.wsh.common.orm.mysql.mybatis.bean.BigAndSmall;
import org.wsh.common.orm.mysql.mybatis.bean.Column;
import org.wsh.common.orm.mysql.mybatis.bean.Table;
import org.wsh.common.orm.mysql.mybatis.bean.TableWapper;
import org.wsh.common.orm.mysql.mybatis.enums.OutPathKey;
import org.wsh.common.orm.mysql.mybatis.util.Util;

import java.util.*;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
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
        String serviceTestPackage = tableWapper.getServiceTestPackage();
        Table table = tableWapper.getTable();
        List<BigAndSmall> column = new ArrayList<BigAndSmall>();
        List<Column> columnList = table.getColumns();
        String outPath = tableWapper.getOutPathMap().get(OutPathKey.DEFULT);
        if(tableWapper.getOutPathMap().get(getOutPath()) != null){
            outPath = tableWapper.getOutPathMap().get(getOutPath());
        }
        String tableName = table.getName();
        String bigDoName = Util.getUpperHumpName(tableName)+"DO";
        String minDoName = Util.getHumpName(tableName)+"DO";
        String bigServiceName = Util.getUpperHumpName(tableName)+"Service";
        String minServiceName = Util.getHumpName(tableName)+"Service";
        String bigServiceTestName = bigServiceName+"Test";
        for (Column co : columnList) {
            Column colu = new Column();
            BigAndSmall bigSmall = new BigAndSmall();
            String type = co.getType();
            if (type.equalsIgnoreCase("DATETIME") || type.equalsIgnoreCase("DATE") || type.equalsIgnoreCase("TIME")
                    || type.equalsIgnoreCase("TIMESTAMP")) {
                type = "Date";
            } else if (type.equalsIgnoreCase("VARCHAR") || type.equalsIgnoreCase("CHAR")
                    || type.equalsIgnoreCase("BLOB") || type.equalsIgnoreCase("TEXT")
                    || type.equalsIgnoreCase("LONGBLOB") || type.equalsIgnoreCase("LONGTEXT")) {
                type = "String";
            } else if (type.equalsIgnoreCase("ENUM")) {
                type = Util.getUpperHumpName(co.getName());
            } else if (type.equalsIgnoreCase("INT") || type.equalsIgnoreCase("TINYINT")) {
                type = "int";
            } else if (type.equalsIgnoreCase("BIGINT")) {
                type = "Long";
            } else if (type.equalsIgnoreCase("DOUBLE")) {
                type = "Double";
            } else if (type.equalsIgnoreCase("FLOAT")) {
                type = "Float";
            } else if (type.equalsIgnoreCase("INTEGER")) {
                type = "Integer";
            }else if (type.equalsIgnoreCase("DECIMAL")) {
                type = "BigDecimal";
            }
            if(Util.getHumpName(co.getName()).equalsIgnoreCase("id")||
                    Util.getHumpName(co.getName()).equalsIgnoreCase("gmtCreated")||
                    Util.getHumpName(co.getName()).equalsIgnoreCase("gmtModified")) continue;
            colu.setType(type);
            colu.setRemark(co.getRemark());
            colu.setName(Util.getHumpName(co.getName()));
            bigSmall.setBigName(Util.getUpperHumpName(co.getName()));
            bigSmall.setSmallName(Util.getHumpName(co.getName()));
            bigSmall.setType(type);
            column.add(bigSmall);
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("servicePackage", servicePackage);
        map.put("serviceTestPackage", serviceTestPackage);
        map.put("bigDoName", bigDoName);
        map.put("minDoName", minDoName);
        map.put("bigServiceName", bigServiceName);
        map.put("minServiceName", minServiceName);
        map.put("columnList", column);
        map.put("date", parseDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        Util.writeCode("serviceTest", map,outPath+bigServiceTestName+".java/");
    }
}

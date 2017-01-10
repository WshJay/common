package org.wsh.common.util.sql;

import org.wsh.common.util.reflect.ReflectUtils;
import org.wsh.common.util.trans.StringUtil;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-01-09 16:54
 */
public class SqlUtil {

    public static String createInsertSql(TableDO tableDO){
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO ").append(tableDO.getTableName()).append(" (");
        String[] columns = ReflectUtils.getFields(tableDO.getClass());
        for (int i = 0; i < columns.length; i++) {
            if (i == 0){
                sql.append(StringUtil.underscoreName(columns[i]));
            }else{
                sql.append(",").append(StringUtil.underscoreName(columns[i]));
            }

        }
        sql.append(") VALUES (");
        for (int i = 0; i < columns.length; i++) {
            if (i == 0){
                sql.append(ReflectUtils.getFieldValue(tableDO.getTable().getClass(),columns[i]));
            }else{
                sql.append(",").append(ReflectUtils.getFieldValue(tableDO.getTable().getClass(),columns[i]));
            }
        }
        sql.append(");");
        return sql.toString();
    }
}

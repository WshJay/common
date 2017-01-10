package org.wsh.common.util.sql;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-01-09 15:10
 */
public class TableDO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表名
     */
    @Getter
    @Setter
    private String tableName;

    /**
     * 表信息
     */
    @Getter
    @Setter
    private T table;

    /**
     * 表信息列表
     */
    @Getter
    @Setter
    private List<T> tableList;

}

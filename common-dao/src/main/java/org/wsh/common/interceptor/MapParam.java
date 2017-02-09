package org.wsh.common.interceptor;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-02-09 15:27
 */
public class MapParam {

    @Getter
    @Setter
    private List<Long> list;

    @Getter
    @Setter
    private String ids;

    @Getter
    @Setter
    private String keyField;

    @Getter
    @Setter
    private String valueField;


    public MapParam() {

    }

    public MapParam(String keyField, String valueField, String ids) {
        this.keyField = keyField;
        this.valueField = valueField;
        this.ids = ids;
    }

    public MapParam(String keyField, String valueField, List<Long> list) {
        this.keyField = keyField;
        this.valueField = valueField;
        this.list = list;
    }

}

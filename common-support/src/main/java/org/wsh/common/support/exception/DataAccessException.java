package org.wsh.common.support.exception;

/**
 * 数据持久层异常
 * Project:     <common-util>
 * File Name:   <DataAccessException.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2015-6-16 下午10:04:34
 */
public class DataAccessException extends RuntimeException {

    private static final long serialVersionUID = -614320429973523172L;

    public DataAccessException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DataAccessException(String s) {
        super(s);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DataAccessException(String s, Throwable throwable) {
        super(s, throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DataAccessException(Throwable throwable) {
        super(throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }

}


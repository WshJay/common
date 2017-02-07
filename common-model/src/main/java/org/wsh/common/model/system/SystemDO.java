package org.wsh.common.model.system;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  系统信息
 * since Date： 2017-02-07 13:37
 */
public class SystemDO implements Serializable{

    /**
     * 运行系统
     */
    @Setter
    @Getter
    private String runSystem;

    /**
     * java版本
     */
    @Setter
    @Getter
    private String javaVersion;

    /**
     * 最大内存
     */
    @Setter
    @Getter
    private double maxMemory;

    /**
     * 总内存
     */
    @Setter
    @Getter
    private double totalMemory;

    /**
     * 已使用内存
     */
    @Setter
    @Getter
    private double usedMemory;

    /**
     * 空闲内存
     */
    @Setter
    @Getter
    private double freeMemory;

    public SystemDO() {
    }

    public SystemDO(String runSystem, String javaVersion, double maxMemory, double totalMemory, double usedMemory, double freeMemory) {
        this.runSystem = runSystem;
        this.javaVersion = javaVersion;
        this.maxMemory = maxMemory;
        this.totalMemory = totalMemory;
        this.usedMemory = usedMemory;
        this.freeMemory = freeMemory;
    }
}

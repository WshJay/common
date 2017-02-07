package org.wsh.common.test.jvm;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  获取当前系统内存
 * since Date： 2017-02-07 11:50
 */
public class Memory {

    public static void main(String[] args) {
        String os = System.getProperty("os.name");
        String javaVersion = System.getProperty("java.version");
        double freeMemory = Math.round((double) Runtime.getRuntime().freeMemory()/ 1024 / 1024);
        double totalMemory = Math.round((double) Runtime.getRuntime().totalMemory()/ 1024 / 1024);
        double maxMemory = Math.round((double) Runtime.getRuntime().maxMemory()/ 1024 / 1024);
        double usedMemory = totalMemory - freeMemory;

        System.out.println("OS: " + os);
        System.out.println("JavaVersion: " + javaVersion);
        System.out.println("MaxMemory: " + totalMemory);
        System.out.println("TotalMemory: " + totalMemory);
        System.out.println("UsedMemory: " + usedMemory);
        System.out.println("FreeMemory: " + freeMemory);
    }

}

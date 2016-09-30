package org.wsh.common.test.exception;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 对此类的描述，可以引用系统设计中的描述
 * @since Date： 2016-09-12 15:40
 */
public class MethodException {

    public void methodOne() throws Exception{
        try {
            int i = 1/0;
        }catch (Exception e){
            throw e;
        }
    }

    public void methodTwo() throws Exception {

        try {
            System.out.println("Test Start...");
            try {
                methodOne();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            System.out.println("Test End...");
        }catch (Exception e){
            throw e;
        }

    }
}

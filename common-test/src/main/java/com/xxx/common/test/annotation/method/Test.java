package com.xxx.common.test.annotation.method;

import com.xxx.common.test.annotation.type.UserNameAnnotations;

import java.lang.reflect.Method;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 一个注解的测试类
 * @since Date： 2016-08-25 17:17
 */
//注入注解作用于类上面
//可以通过反射 获取类的信息之后 获取得到这个注解的值
@UserNameAnnotations(value = "initphp")
public class Test {

    private String userName;

    //注解到
    @MethodType(methodType= MethodType.MethodTypeEnum.TYPE2)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static void main(String[] args) {
        Class<Test> testClass = Test.class;
        try {
            //因为是注解到method上的，所以首先要获取这个方法
            Method method = testClass.getDeclaredMethod("getUserName");

            //判断这个方法上是否有这个注解
            if (method.isAnnotationPresent(MethodType.class)) {
                System.out.println("this is a method Annotation");

                //如果有这个注解，则获取注解类
                MethodType methodType = (MethodType) method.getAnnotation(MethodType.class);
                if (methodType != null) {
                    if (MethodType.MethodTypeEnum.TYPE1.equals(methodType.methodType())) {
                        System.out.println("this is TYPE1");
                    } else {
                        System.out.println("this is TYPE2");
                    }
                }
            } else {
                System.out.println("this is not  a method Annotation");
            }

        } catch (Exception e) {
        }

    }

}

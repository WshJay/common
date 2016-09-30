package org.wsh.common.test.annotation.type;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 一个注解的测试类
 * @since Date： 2016-08-25 17:12
 */
//注入注解作用于类上面
//可以通过反射 获取类的信息之后 获取得到这个注解的值
@UserNameAnnotations(value = "initphp")
public class Test {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static void main(String[] args) {
        Class<Test> testClass = Test.class;
        //因为注解是作用于类上面的，所以可以通过isAnnotationPresent来判断是否是一个
        //有UserNameAnnotations注解的类
        if (testClass.isAnnotationPresent(UserNameAnnotations.class)) {
            System.out.println("this is a Annotations class");
            //通过getAnnotation可以获取注解对象
            UserNameAnnotations userNameAnnotations = (UserNameAnnotations) testClass.
                    getAnnotation(UserNameAnnotations.class);
            if (userNameAnnotations != null) {
                System.out.println("value:" + userNameAnnotations.value());
            } else {
                System.out.println("null");
            }
        } else {
            System.out.println("this is not Annotations class");
        }

    }

}

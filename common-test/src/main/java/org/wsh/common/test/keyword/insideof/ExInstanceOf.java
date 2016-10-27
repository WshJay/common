package org.wsh.common.test.keyword.insideof;

/**
 * instanceof运算符用法
 * 运算符是双目运算符,左面的操作元是一个对象,右面是一个类.当
 * 左面的对象是右面的类创建的对象时,该运算符运算的结果是true,否则是false
 * 说明:(1)一个类的实例包括本身的实例,以及所有直接或间接子类的实例
 * (2)instanceof左边操作元显式声明的类型与右边操作元必须是同种类或右边是左边父类的继承关系,
 * (3)不同的继承关系下,编译出错
 */
public class ExInstanceOf {

    public static void main(String[] args) {
        instanceofTest(new Student());
        instanceofTest();

    }

    private static void instanceofTest() {
        A ab=new B();
        A ac=new C();
        B bc=new C();
        B bb=new B();
        C cc=new C();
        //对象实现一个接口，用这个对象和这个接口进行instanceof判断，都为true。
        System.out.println("ab instanceof A="+(ab instanceof A));
        System.out.println("ac instanceof A="+(ac instanceof A));
        System.out.println("bc instanceof A="+(bc instanceof A));
        System.out.println("bb instanceof A="+(bb instanceof A));
        System.out.println("cc instanceof A="+(cc instanceof A));
        //对象和父类进行instanceof判断，都为true
        System.out.println("ab instanceof B="+(ab instanceof B));
        System.out.println("ac instanceof B="+(ac instanceof B));
        System.out.println("bc instanceof B="+(bc instanceof B));
        System.out.println("bb instanceof B="+(bb instanceof B));
        System.out.println("cc instanceof B="+(cc instanceof B));
        //对象和他的子类进行instanceof判断为false
        System.out.println("ab instanceof C="+(ab instanceof C));
        System.out.println("ac instanceof C="+(ac instanceof C));
        System.out.println("bc instanceof C="+(bc instanceof C));
        System.out.println("bb instanceof C="+(bb instanceof C));
        System.out.println("cc instanceof C="+(cc instanceof C));
    }

    /**
     * 这个程序的输出结果是：p是类Student的实例
     * Person类所在的继承树是：Object<--Person<--Student<--Postgraduate。
     * 这个例子中还加入一个Animal类，它不是在Person类的继承树中，所以不能作为instanceof的右操作数。
     * @param p
     */
    public static void instanceofTest(Person p) {
        // p 和 Animal类型不一样,彼此之间没有继承关系,编译会出错
        // 提示错误:Incompatible conditional operand types Person and Animal
        // if(p instanceof Animal){
        // System.out.println("p是类Animal的实例");
        // }
        //下面代码的除了第一行都会输出
        if (p instanceof Postgraduate) System.out.println("p是类Postgraduate的实例");
        if (p instanceof Person) System.out.println("p是类Person的实例");
        if (p instanceof Student) System.out.println("p是类Student的实例");
        if (p instanceof Object) System.out.println("p是类Object的实例");
    }
}

class Person {}

class Student extends Person {}

class Postgraduate extends Student {}

class Animal {}

interface A{}

class B implements A{}

class C extends B{}

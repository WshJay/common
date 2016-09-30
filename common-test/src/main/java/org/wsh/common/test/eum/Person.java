package org.wsh.common.test.eum;

import lombok.Getter;
import lombok.Setter;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-09-28 16:27
 */
public enum Person implements PersonExtends {

    MAN("zhangsan", "12") {
        public void work() {
            System.out.println("能干体力活，承受得住重量");
        }

    }, WOMEN("lisi", "13") {
        public void work() {
            System.out.println("职场白领居多，能者居之");
        }
    };

    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String age;

    private Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

    @Override
    public void work() {
        // TODO Auto-generated method stub
        System.out.println("男人和女人工作的强度不同");
    }

}

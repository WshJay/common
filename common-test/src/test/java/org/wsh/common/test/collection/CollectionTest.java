package org.wsh.common.test.collection;

import ch.lambdaj.Lambda;
import ch.lambdaj.collection.LambdaCollection;
import ch.lambdaj.collection.LambdaCollections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.model.basic.MenuDO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static ch.lambdaj.Lambda.collect;
import static ch.lambdaj.Lambda.on;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-09-28 11:13
 */
public class CollectionTest {

    @Test
    public void test(){
        List<MenuDO> menuDOList = new ArrayList<MenuDO>();
        MenuDO menuDO = new MenuDO();
        menuDO.setId(1l);
        menuDO.setName("1");

        MenuDO menuDO1 = new MenuDO();
        menuDO1.setId(2l);
        menuDO1.setName("2");
        menuDOList.add(menuDO);
        menuDOList.add(menuDO1);
        List<Long> ids = Lambda.collect(menuDOList,on(MenuDO.class).getId());
        System.out.println(ids.size());
        for (Long id : ids) {
            System.out.println("id==>" + id);
        }

        System.out.println(menuDOList.size());
    }
}

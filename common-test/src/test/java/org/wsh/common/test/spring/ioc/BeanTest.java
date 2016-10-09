package org.wsh.common.test.spring.ioc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;

import org.wsh.common.model.basic.MenuDO;
import org.wsh.common.service.api.MenuService;
import org.wsh.common.test.service.TestService;
import org.wsh.common.util.logger.LoggerService;

import java.util.List;

import static org.wsh.common.enums.SessionKey.userName;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml","classpath:/common-bean.xml"})
public class BeanTest extends LoggerService {
	
	@Autowired
	private TestService testService;

	@Autowired
	private MenuService menuService;

	@Test
	public void test(){
		testService.test();
		System.out.println(userName);

		List<MenuDO> menuDOList = menuService.getAllRootMenu();
		logger.info("size:" + menuDOList.size());
	}
}


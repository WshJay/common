package org.common.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xxx.common.dao.UserDao;
import com.xxx.common.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml"})
public class EntityTest {

	@Autowired
	private JpaRepository<User, Long> userDao;
	
	private final Logger log = LoggerFactory.getLogger(EntityTest.class);
	
	public EntityTest(JpaRepository<User, Long> userDao) {
		this.userDao = userDao;
	}
	
	@Test()
	public void test(){
//		User user = userDao.findByUsername("admin");
//		log.info(user.getPassword());
	}
}


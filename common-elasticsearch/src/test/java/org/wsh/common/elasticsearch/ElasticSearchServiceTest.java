package org.wsh.common.elasticsearch;

import com.wsh.common.elasticsearch.domain.City;
import com.wsh.common.elasticsearch.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.wsh.common.elasticsearch.Application;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ElasticSearchServiceTest {

    @Resource
    private CityService cityService;

    @Test
    public void esTest(){
        City city = new City();
        city.setId(1L);
        city.setName("杭州");
        city.setDescription("杭州");
        city.setScore(1000);
        Long result = cityService.saveCity(city);
//        logger.info("resultNum==>{}", result);
    }
}

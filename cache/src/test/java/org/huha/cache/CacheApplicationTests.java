package org.huha.cache;

import org.huha.cache.demo.domain.City;
import org.huha.cache.demo.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate; //操作 k-v 字符串

    @Autowired
    RedisTemplate redisTemplate;  //k- v 都是对象

    @Autowired
    CityService cityService;

    @Test
    public void contextLoads() {
        City city = new City();
        city.setCountry("CN");
        city.setName("WK");
        city.setState("HB");
        cityService.save(city);
    }

    @Test
    public void test17252() {
        System.out.println(cityService.get(1L));
        System.out.println(cityService.get(2L));
    }

    @Test
    public void test183423() {
        cityService.del(1L);
//        cityService.del(2L);
//        cityService.delAll();
    }

    @Test
    public void test18351() {
        City city = new City();
        city.setId(1L);
        city.setState("NN");
        city.setName("bb");
        city.setCountry("CC");
        cityService.update(city);
    }


}


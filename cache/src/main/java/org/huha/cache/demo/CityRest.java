package org.huha.cache.demo;

import org.huha.cache.demo.domain.City;
import org.huha.cache.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangKun
 * @create 2019-01-30
 * @desc
 **/
@RestController
public class CityRest {
    @Autowired
    CityService cityService;

    @RequestMapping("/city/save")
    public void save(City city) {
        cityService.save(city);
    }

    @RequestMapping("/city/get")
    public City get(Long id) {
        return cityService.get(id);
    }

    @RequestMapping("/city/update")
    public void update(City city) {
        cityService.update(city);
    }

    @RequestMapping("/city/del")
    public void del(Long id) {
        cityService.del(id);
    }

    @RequestMapping("/city/delAll")
    public void delAll() {
        cityService.delAll();
    }
}

package org.huha.cache.demo.service;

import org.huha.cache.demo.domain.City;
import org.huha.cache.demo.mapper.CityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author WangKun
 * @create 2019-01-30
 * @desc
 **/
@Service
@CacheConfig(cacheNames = "cityService")
public class CityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityService.class);

    @Autowired
    CityMapper cityMapper;

    @CachePut(key = "#city.id")
    public void save(City city) {
        Long id = cityMapper.save(city);
        LOGGER.info("save::city = {},id = {}", city, id);
    }

    @Cacheable(key = "#id")
    public City get(Long id) {
        LOGGER.info("get::id = {}", id);
        return cityMapper.findById(id);
    }

    @CachePut(key = "#city.id")
    public City update(City city) {
        Long aLong = cityMapper.updateById(city);
        if (aLong > 0) {
            return cityMapper.findById(city.getId());
        }
        return null;
    }

    @CacheEvict(key = "#id")
    public void del(Long id) {
        LOGGER.info("del::id = {}", id);
        cityMapper.del(id);
    }

    @CacheEvict(allEntries = true)
    public void delAll() {
        cityMapper.delAll();
    }

}

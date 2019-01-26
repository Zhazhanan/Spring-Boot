package com.example.mybatisdemo.demo.mapper;

import com.example.mybatisdemo.demo.domain.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author WangKun
 * @create 2019-01-26 上午 10:33
 * @desc
 **/
@Mapper
public interface CityMapper {

    @Select("SELECT id, name, state, country FROM city WHERE state = #{state}")
    City findByState(String state);

}

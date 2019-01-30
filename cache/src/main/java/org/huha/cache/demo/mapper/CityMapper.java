package org.huha.cache.demo.mapper;


import org.apache.ibatis.annotations.*;
import org.huha.cache.demo.domain.City;

/**
 * @author WangKun
 * @create 2019-01-26 上午 10:33
 * @desc
 **/
@Mapper
public interface CityMapper {

    @Insert("INSERT INTO city (name, state, country) VALUES(#{name},#{state},#{country})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Long save(City city);

    @Select("SELECT id, name, state, country FROM city WHERE id = #{id}")
    City findById(Long id);

    @Update("UPDATE city set name = #{name}, state = #{state}, country = #{country} where id = #{id}")
    Long updateById(City city);

    @Delete("DELETE from city where id = #{id}")
    void del(Long id);

    @Delete("DELETE from city")
    void delAll();
}

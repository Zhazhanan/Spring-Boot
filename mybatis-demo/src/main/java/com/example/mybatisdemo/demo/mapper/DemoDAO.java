package com.example.mybatisdemo.demo.mapper;


import com.example.mybatisdemo.demo.domain.DemoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author dell
 * @date 2019-01-19 14:09:15
 */
@Mapper
public interface DemoDAO {
    List<DemoDTO> searchDemoByPaging(DemoDTO dto);

    DemoDTO findDemoByPrimaryKey(DemoDTO dto);

    @Select("select * from demo where id = #{id}")
    DemoDTO findById(@Param("id") String id);
}

package com.example.elasticsearch.controller;

import com.example.elasticsearch.utils.ElasticsearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangKun
 * @create 2018-08-23
 * @desc
 **/
@RestController
public class ESController {
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    ElasticsearchUtils elasticsearchUtils;

    @PostMapping("/es/add")
    public boolean addIndex(String index) {
        return elasticsearchTemplate.createIndex(index);
//        return elasticsearchUtils.createIndex(index);
    }

    @DeleteMapping("/es/del")
    public boolean deleteIndex(String index) {
        return elasticsearchTemplate.deleteIndex(index);
//        return elasticsearchUtils.deleteIndex(index);
    }
}

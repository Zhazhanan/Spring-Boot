package com.example.elasticsearch;

import com.example.elasticsearch.pojo.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author WangKun
 * @create 2018-08-22 下午 5:19
 * @desc
 **/
public interface BookRepository extends ElasticsearchRepository<Book, Integer> {

}

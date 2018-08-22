package com.example.elasticsearch;

import com.example.elasticsearch.pojo.Book;
import com.example.elasticsearch.utils.ElasticsearchUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTests {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ElasticsearchUtils elasticsearchUtils;

    @Test
    public void contextLoads() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().build();
        List<Book> books = elasticsearchTemplate.queryForList(searchQuery, Book.class);
        for (Book book : books) {
            System.out.println(book);
        }
        Book book = bookRepository.findById(2).get();
        System.out.println(book);
        Map<String, Object> map = elasticsearchUtils.searchDataById("bookindex", "booktype", "2", null);
        List<Map<String, Object>> list = elasticsearchUtils.searchListData("bookindex", "booktype", "bookName", "西");
        System.out.println(map);
        System.out.println(list);

    }

}

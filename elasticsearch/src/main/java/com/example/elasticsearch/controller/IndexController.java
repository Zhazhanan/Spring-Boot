package com.example.elasticsearch.controller;

import com.example.elasticsearch.BookRepository;
import com.example.elasticsearch.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;

/**
 * @author WangKun
 * @create 2018-08-10
 * @desc
 **/
@RestController("/book")
public class IndexController {
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/add")
    public Book addIndex(@Valid Book book) {
        Book save = bookRepository.save(book);
        return save;
    }

    @DeleteMapping("/del")
    public void deleteIndex(Book book) {
        bookRepository.delete(book);
    }

    @GetMapping("/books")
    public List<Book> books() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().build();
        List<Book> books = elasticsearchTemplate.queryForList(searchQuery, Book.class);
        return books;
    }

    @GetMapping("/findByNameLike")
    public List<Book> findByNameLike(String bookName, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchPhraseQuery("bookName", bookName)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Book.class);
    }
}

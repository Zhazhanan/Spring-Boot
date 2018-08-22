package com.example.elasticsearch.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author WangKun
 * @create 2018-08-22
 * @desc
 **/
@Document(indexName = "bookindex", type = "booktype")
@Data
public class Book {
    @Id
    private Integer id;
    private String bookName;
    private String author;
}

package com.example.elasticsearch.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author WangKun
 * @create 2018-08-22
 * @desc
 **/
@Document(indexName = "bookindex", type = "booktype")
@Data
public class Book implements Serializable {
    @Id
    @NotNull(message = "图书ID不能为空！")
    private Integer id;
    @NotNull(message = "图书名称不能为空！")
    private String bookName;
    @NotNull(message = "图书作者不能为空！")
    private String author;
}

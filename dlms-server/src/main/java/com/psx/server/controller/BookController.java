package com.psx.server.controller;

import com.psx.server.pojo.Book;
import com.psx.server.service.IBookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author psx
 * @date 2021/4/8 9:55
 */
@RestController
public class BookController {
    @Autowired
    private IBookService bookService;


    @ApiOperation(value = "获取所有图书")
    @GetMapping
    public List<Book> getBookList(){
        return bookService.getBookList();
    }

    @ApiOperation(value = "根据类型获取图书")
    @GetMapping
    public List<Book> getBookListByType(@RequestBody String type){
        return bookService.getBookListByType(type);
    }
}

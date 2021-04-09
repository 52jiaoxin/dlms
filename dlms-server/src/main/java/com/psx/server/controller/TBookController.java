package com.psx.server.controller;

import com.psx.server.pojo.TBook;
import com.psx.server.service.ITBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author psx
 * @date 2021/4/8 9:55
 */
@RestController
@Api(tags="BookController")
public class TBookController {
    @Autowired
    private ITBookService bookService;


    @ApiOperation(value = "获取所有图书")
    @GetMapping("/book/list")
    public List<TBook> getBookList(){
        return bookService.getBookList();
    }

    @ApiOperation(value = "根据类型获取图书")
    @GetMapping("/book/{type}")
    public List<TBook> getBookListByType(@RequestBody  @PathVariable("type") String type){
        return bookService.getBookListByType(type);
    }
}

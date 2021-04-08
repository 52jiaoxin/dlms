package com.psx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.psx.server.pojo.Book;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author psx
 * @since 2021-04-08
 */
public interface IBookService extends IService<Book> {

    List<Book> getBookList();

    List<Book> getBookListByType(String type);
}

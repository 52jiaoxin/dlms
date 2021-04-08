package com.psx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.psx.server.pojo.Book;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author psx
 * @since 2021-04-08
 */
@Component
public interface BookMapper extends BaseMapper<Book> {

    List<Book> getBookList();

    List<Book> getBookListByType(String type);
}

package com.psx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.psx.server.pojo.TBook;
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
public interface TBookMapper extends BaseMapper<TBook> {

    List<TBook> getBookList();

    List<TBook> getBookListByType(String type);
}

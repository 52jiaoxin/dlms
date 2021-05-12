package com.psx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.psx.server.pojo.TBook;
import org.apache.ibatis.annotations.Param;
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


    IPage<TBook> getBookByPage(Page<TBook> page, @Param("book")TBook book);

    List<TBook> exportBook(Integer id);

    List<String> getBookName();

    List<Integer> getNum();

    Integer getTotal();

    List<Integer> getUserNum();

    List<String> getUserNickName();

    Integer getUserTotal();
}

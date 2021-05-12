package com.psx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.psx.server.pojo.RespPageBean;
import com.psx.server.pojo.TBook;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author psx
 * @since 2021-04-08
 */
public interface ITBookService extends IService<TBook> {

    List<TBook> getBookList();

    List<TBook> getBookListByType(String type);

    RespPageBean getBookByPage(Integer currentPage, Integer size,TBook book);

    List<TBook> exportBook(Integer id);

    boolean updateByIds(Integer[] borrowids);

    List<String> getBookName();

    List<Integer> getNum();

    Integer getTotal();

    List<Integer> getUserNum();

    List<String> getUserNickName();

    Integer getUserTotal();
}

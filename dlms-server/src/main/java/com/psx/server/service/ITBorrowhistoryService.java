package com.psx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.psx.server.pojo.RespBean;
import com.psx.server.pojo.RespPageBean;
import com.psx.server.pojo.TBorrowhistory;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author psx
 * @since 2021-04-09
 */
public interface ITBorrowhistoryService extends IService<TBorrowhistory> {

    RespPageBean getBorrowHistory(Integer currentPage, Integer size, Integer userid, Integer bookid, String username, String bookname);

    RespBean ReBorrow(TBorrowhistory borrowHistory);
}

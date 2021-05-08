package com.psx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.psx.server.pojo.RespPageBean;
import com.psx.server.pojo.TReaderhistory;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author psx
 * @since 2021-04-09
 */
public interface ITReaderhistoryService extends IService<TReaderhistory> {

    RespPageBean getReaderHistory(Integer currentPage, Integer size, Integer userid, Integer bookid, String username, String bookname);

    boolean clear();
}

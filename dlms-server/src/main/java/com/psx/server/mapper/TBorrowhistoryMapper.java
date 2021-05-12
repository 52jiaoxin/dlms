package com.psx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.psx.server.pojo.TBorrowhistory;
import org.springframework.stereotype.Component;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author psx
 * @since 2021-04-09
 */
@Component
public interface TBorrowhistoryMapper extends BaseMapper<TBorrowhistory> {

    IPage<TBorrowhistory> getBorrowHistory(Page<TBorrowhistory> page,Integer userid, Integer bookid, String nickname, String bookname);
}

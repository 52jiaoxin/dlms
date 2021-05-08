package com.psx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.psx.server.pojo.TReaderhistory;
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
public interface TReaderhistoryMapper extends BaseMapper<TReaderhistory> {

    IPage<TReaderhistory> getReaderHistory(Page<TReaderhistory> page, Integer userid, Integer bookid, String username, String bookname);

    int clear();
}

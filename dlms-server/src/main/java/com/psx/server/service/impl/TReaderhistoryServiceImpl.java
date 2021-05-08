package com.psx.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.psx.server.mapper.TReaderhistoryMapper;
import com.psx.server.pojo.RespPageBean;
import com.psx.server.pojo.TReaderhistory;
import com.psx.server.service.ITReaderhistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author psx
 * @since 2021-04-09
 */
@Service
public class TReaderhistoryServiceImpl extends ServiceImpl<TReaderhistoryMapper, TReaderhistory> implements ITReaderhistoryService {

    @Autowired
    private TReaderhistoryMapper readerhistoryMapper;

    @Override
    public RespPageBean getReaderHistory(Integer currentPage, Integer size,Integer userid,Integer bookid,String username,String bookname) {
        Page<TReaderhistory> page=new Page<>(currentPage,size);
        IPage<TReaderhistory> bookIPage=readerhistoryMapper.getReaderHistory(page,userid,bookid,username,bookname);
        RespPageBean respPageBean=new RespPageBean(bookIPage.getTotal(),bookIPage.getRecords());
        return respPageBean;

    }

    @Override
    public boolean clear() {
        if(readerhistoryMapper.clear()==0){
            return true;
        }
        return false;
    }
}

package com.psx.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.psx.server.mapper.TBorrowhistoryMapper;
import com.psx.server.pojo.RespBean;
import com.psx.server.pojo.RespPageBean;
import com.psx.server.pojo.TBorrowhistory;
import com.psx.server.service.ITBorrowhistoryService;
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
public class TBorrowhistoryServiceImpl extends ServiceImpl<TBorrowhistoryMapper, TBorrowhistory> implements ITBorrowhistoryService {

    @Autowired
    private TBorrowhistoryMapper borrowhistoryMapper;


    @Override
    public RespPageBean getBorrowHistory(Integer currentPage, Integer size,Integer userid,Integer bookid,String username,String bookname) {
        Page<TBorrowhistory> page=new Page<>(currentPage,size);
        IPage<TBorrowhistory> bookIPage=borrowhistoryMapper.getBorrowHistory(page,userid,bookid,username,bookname);
        RespPageBean respPageBean=new RespPageBean(bookIPage.getTotal(),bookIPage.getRecords());
        return respPageBean;

    }

    @Override
    public RespBean ReBorrow(TBorrowhistory borrowHistory) {
        int renum=borrowHistory.getRenum();
        if(renum!=0){
            borrowHistory.setRenum(renum-1);
            if(borrowhistoryMapper.updateById(borrowHistory)!=0){
                return RespBean.success("续借成功");
            }
            else{
                return RespBean.error("续借失败");
            }
        }
        return RespBean.error("续借失败，可续借次数为0");
    }
}

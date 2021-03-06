package com.psx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.psx.server.mapper.TBookMapper;
import com.psx.server.mapper.TBorrowhistoryMapper;
import com.psx.server.pojo.RespPageBean;
import com.psx.server.pojo.TBook;
import com.psx.server.pojo.TBorrowhistory;
import com.psx.server.service.ITBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author psx
 * @since 2021-04-08
 */
@Service
public class TBookServiceImpl extends ServiceImpl<TBookMapper, TBook> implements ITBookService {

    @Autowired
    private TBookMapper tBookMapper;

    @Autowired
    private TBorrowhistoryMapper borrowhistoryMapper;


    @Override
    public List<TBook> getBookList() {
        return tBookMapper.getBookList();
    }

    @Override
    public List<TBook> getBookListByType(String type) {
        return tBookMapper.getBookListByType(type);
    }

    @Override
    public RespPageBean getBookByPage(Integer currentPage, Integer size,TBook book) {
//        开启分页
        Page<TBook> page=new Page<>(currentPage,size);
        IPage<TBook> bookIPage=tBookMapper.getBookByPage(page,book);
        RespPageBean respPageBean=new RespPageBean(bookIPage.getTotal(),bookIPage.getRecords());
        return respPageBean;
    }

    @Override
    public List<TBook> exportBook(Integer id) {
        return baseMapper.exportBook(id);
    }

    @Override
    public boolean updateByIds(Integer[] borrowids) {
        for(int i=0;i<borrowids.length;i++){
            int bid=(borrowhistoryMapper.selectOne(new QueryWrapper<TBorrowhistory>().eq("id",borrowids[i]))).getBookid();
            TBook book=tBookMapper.selectOne(new QueryWrapper<TBook>().eq("id",bid));
            book.setTotal_number(book.getTotal_number()+1);
            if(tBookMapper.updateById(book)==0){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<String> getBookName() {
        return tBookMapper.getBookName();
    }

    @Override
    public List<Integer> getNum() {
        return tBookMapper.getNum();
    }

    @Override
    public Integer getTotal() {
        return tBookMapper.getTotal();
    }

    @Override
    public List<Integer> getUserNum() {
        return tBookMapper.getUserNum();
    }

    @Override
    public List<String> getUserNickName() {
        return tBookMapper.getUserNickName();
    }

    @Override
    public Integer getUserTotal() {
        return tBookMapper.getUserTotal();
    }
}

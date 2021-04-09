package com.psx.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.psx.server.mapper.TBookMapper;
import com.psx.server.pojo.TBook;
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
    private TBookMapper TBookMapper;


    @Override
    public List<TBook> getBookList() {
        return TBookMapper.getBookList();
    }

    @Override
    public List<TBook> getBookListByType(String type) {
        return TBookMapper.getBookListByType(type);
    }
}

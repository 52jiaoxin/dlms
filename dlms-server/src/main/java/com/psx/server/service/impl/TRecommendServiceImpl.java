package com.psx.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.psx.server.mapper.TRecommendMapper;
import com.psx.server.pojo.TBook;
import com.psx.server.pojo.TRecommend;
import com.psx.server.service.ITRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author psx
 * @since 2021-04-20
 */
@Service
public class TRecommendServiceImpl extends ServiceImpl<TRecommendMapper, TRecommend> implements ITRecommendService {

    @Autowired
    private TRecommendMapper recommendMapper;

    @Override
    public List<TBook> getRecommend() {
        return recommendMapper.getRecommend();
    }
}

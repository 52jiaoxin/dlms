package com.psx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.psx.server.pojo.TBook;
import com.psx.server.pojo.TRecommend;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author psx
 * @since 2021-04-20
 */
public interface ITRecommendService extends IService<TRecommend> {

    List<TBook> getRecommend();
}

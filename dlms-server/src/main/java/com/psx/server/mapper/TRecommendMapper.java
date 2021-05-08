package com.psx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.psx.server.pojo.TBook;
import com.psx.server.pojo.TRecommend;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author psx
 * @since 2021-04-20
 */
@Component
public interface TRecommendMapper extends BaseMapper<TRecommend> {

    List<TBook> getRecommend();
}

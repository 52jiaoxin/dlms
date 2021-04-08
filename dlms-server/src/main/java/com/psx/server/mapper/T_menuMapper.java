package com.psx.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.psx.server.pojo.T_menu;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author psx
 * @since 2021-04-06
 */
@Component
public interface T_menuMapper extends BaseMapper<T_menu> {


    List<T_menu> getMenusByUserId(int id);
}

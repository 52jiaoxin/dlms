package com.psx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.psx.server.pojo.T_menu;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author psx
 * @since 2021-04-06
 */
public interface IT_menuService extends IService<T_menu> {

    List<T_menu> getMenusByUserId();
}

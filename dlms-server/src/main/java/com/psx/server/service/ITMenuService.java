package com.psx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.psx.server.pojo.TMenu;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author psx
 * @since 2021-04-06
 */
public interface ITMenuService extends IService<TMenu> {

    List<TMenu> getMenusByUserId();
}

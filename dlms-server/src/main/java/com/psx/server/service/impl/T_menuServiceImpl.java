package com.psx.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.psx.server.mapper.T_menuMapper;
import com.psx.server.pojo.Admin;
import com.psx.server.pojo.T_menu;
import com.psx.server.service.IT_menuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author psx
 * @since 2021-04-06
 */
@Service
public class T_menuServiceImpl extends ServiceImpl<T_menuMapper, T_menu> implements IT_menuService {
    @Autowired
    private T_menuMapper menuMapper;

    @Override
    public List<T_menu> getMenusByUserId() {
        return menuMapper.getMenusByUserId(((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }
}

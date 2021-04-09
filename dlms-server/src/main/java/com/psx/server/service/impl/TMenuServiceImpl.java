package com.psx.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.psx.server.mapper.TMenuMapper;
import com.psx.server.pojo.TUser;
import com.psx.server.pojo.TMenu;
import com.psx.server.service.ITMenuService;
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
public class TMenuServiceImpl extends ServiceImpl<TMenuMapper, TMenu> implements ITMenuService {
    @Autowired
    private TMenuMapper menuMapper;

    @Override
    public List<TMenu> getMenusByUserId() {
        return menuMapper.getMenusByUserId(((TUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }
}

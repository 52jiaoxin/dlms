package com.psx.server.controller;

import com.psx.server.pojo.TMenu;
import com.psx.server.service.ITMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author psx
 * @date 2021/4/6 18:38
 */
@RestController
@RequestMapping("/sys/cfg")
public class TMenuController {
    @Autowired
    private ITMenuService menuService;

    @GetMapping("/menu")
    public List<TMenu> menus(){
        return menuService.getMenusByUserId();
    }

}

package com.psx.server.controller;

import com.psx.server.pojo.T_menu;
import com.psx.server.service.IT_menuService;
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
public class MenuController {
    @Autowired
    private IT_menuService menuService;

    @GetMapping("/menu")
    public List<T_menu> menus(){
        return menuService.getMenusByUserId();
    }

}

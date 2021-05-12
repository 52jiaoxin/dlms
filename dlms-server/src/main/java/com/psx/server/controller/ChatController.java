package com.psx.server.controller;

import com.psx.server.pojo.TUser;
import com.psx.server.service.ITUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 聊天用户列表
 * @author psx
 * @date 2021/4/21 9:53
 */
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ITUserService userService;

    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/users")
    public List<TUser> getALl(@RequestParam(defaultValue = "") String keywords,
                              @RequestParam(defaultValue = "") Integer id,
                              @RequestParam(defaultValue = "") String role){
        return userService.getUserList(keywords,id,role);
    }



}

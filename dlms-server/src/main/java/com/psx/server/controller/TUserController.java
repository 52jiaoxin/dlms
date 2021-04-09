package com.psx.server.controller;


import com.psx.server.pojo.TUser;
import com.psx.server.service.ITUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author psx
 * @since 2021-03-24
 */
@RestController
public class TUserController {
    @Autowired
    private ITUserService userService;


    @GetMapping("/user/list")
    public List<TUser> UserList(){
        return userService.getUserList();
    }

}

package com.psx.server.controller;

import com.psx.server.pojo.RespBean;
import com.psx.server.pojo.TUser;
import com.psx.server.service.ITUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author psx
 * @date 2021/5/1 9:55
 */
@RestController
public class TestController {
    @Autowired
    private ITUserService userService;


    @ApiOperation(value = "发送邮件")
    @PostMapping("/test/sendMail")
    public RespBean sendMail(String mail, HttpServletRequest request){
        return userService.sendMail(mail,request);
    }

    @ApiOperation(value = "注册")
    @PostMapping("/test/register")
    public RespBean register(@RequestBody TUser user, String code, HttpServletRequest httpServletRequest){

        return userService.register(user,code, httpServletRequest);
    }

}

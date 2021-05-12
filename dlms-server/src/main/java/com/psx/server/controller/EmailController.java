package com.psx.server.controller;

import com.psx.server.pojo.RespBean;
import com.psx.server.service.ITUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 发送邮件
 * @author psx
 * @date 2021/5/1 9:55
 */
@RestController
public class EmailController {
    @Autowired
    private ITUserService userService;


    @ApiOperation(value = "发送邮件")
    @PostMapping("/sendMail")
    public RespBean sendMail(String mail, HttpServletRequest request){
        return userService.sendMail(mail,request);
    }



}

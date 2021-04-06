package com.psx.server.controller;

import com.psx.server.pojo.Admin;
import com.psx.server.pojo.AdminLoginParam;
import com.psx.server.pojo.RespBean;
import com.psx.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录、注销、获取当前登录用户接口
 *
 * @author psx
 * @date 2021/3/23 17:09
 */
@RestController
@Api(tags="LoginController")
public class LoginController {

    @Autowired
    private IAdminService adminService;


    @ApiOperation(value = "用户登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest httpServletRequest){
        RespBean respBean=adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),
                adminLoginParam.getCode(),httpServletRequest);
        return respBean;
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout(){

        return RespBean.success("注销成功");
    }

    @ApiOperation(value = "获取当前登录用户的信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(){
        Admin principal= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//获取SpringSecurity上下文
        if(principal==null)
            return null;
        principal.setPassword(null);
        return  principal;
    }

    @ApiOperation(value = "注册用户")
    @PostMapping("/register")
    public RespBean register(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest httpServletRequest){
        return adminService.register(adminLoginParam.getUsername(),adminLoginParam.getPassword(),
                adminLoginParam.getCode(), httpServletRequest);
    }


}

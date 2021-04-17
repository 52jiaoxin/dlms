package com.psx.server.controller;


import com.psx.server.pojo.RespBean;
import com.psx.server.pojo.TUser;
import com.psx.server.service.ITUserRoleService;
import com.psx.server.service.ITUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ITUserRoleService userRoleService;

    @ApiOperation(value = "获取图书管理员列表")
    @GetMapping("/emp/list")
    public List<TUser> EmpList(@RequestParam(defaultValue = "") String username){
        return userService.getEmpList(username);
    }

    @ApiOperation(value = "获取读者列表")
    @GetMapping("/reader/list")
    public List<TUser> ReaderList(@RequestParam(defaultValue = "") String username){
        return userService.getReaderList(username);
    }

    @ApiOperation(value = "获取图书管理员列表")
    @DeleteMapping("/user/delete/{id}")
    public RespBean DeleteUser(@PathVariable("id") int id){

        if(userRoleService.deleteById(id)&&userService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

}

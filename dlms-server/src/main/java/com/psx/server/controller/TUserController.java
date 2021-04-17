package com.psx.server.controller;


import com.psx.server.pojo.RespBean;
import com.psx.server.pojo.RespPageBean;
import com.psx.server.pojo.TUser;
import com.psx.server.pojo.TUserRole;
import com.psx.server.service.ITUserRoleService;
import com.psx.server.service.ITUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
    @ApiOperation(value = "批量删除读者")
    @DeleteMapping("/user/deletemany")
    public RespBean DeleteManyUser(Integer[] ids){
//因为存在外键，所以需要先删除联系表中的数据
        if(userRoleService.deleteByIds(ids)&&userService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/user/deleteone/{id}")
    public RespBean DeleteUser(@PathVariable("id") int id){
//因为存在外键，所以需要先删除联系表中的数据
        if(userRoleService.deleteById(id)&&userService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "分页查询读者")
    @GetMapping("/user/page/")
    public RespPageBean getPageBook(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10")Integer size,
                                    TUser user){
        return userService.getUserByPage(currentPage,size,user);

    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/user/insert")
    public RespBean addUser(@RequestBody TUser tUser){
//因为存在外键，所以需要在关连表添加数据
        if(userService.save(tUser)){
            TUserRole tUserRole=new TUserRole();
            tUserRole.setUserid(tUser.getId());
            tUserRole.setRid(3);
            if (userRoleService.save(tUserRole))
                return RespBean.success("添加成功");
            else
                return RespBean.error("添加失败");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "更新读者信息")
    @PostMapping("/user/update")
    public RespBean updateBook(@RequestBody TUser tUser){

        if(userService.updateById(tUser)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

}

package com.psx.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.psx.server.pojo.RespBean;
import com.psx.server.pojo.RespPageBean;
import com.psx.server.pojo.TUser;
import com.psx.server.service.ITUserRoleService;
import com.psx.server.service.ITUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    public List<TUser> EmpList(@RequestParam(defaultValue = "") String nickname){
        return userService.getEmpList(nickname);
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
        if(userService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "分页查询读者")
    @GetMapping("/reader/page/")
    public RespPageBean getPageReader(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10")Integer size,
                                    TUser user){
        return userService.getReaderByPage(currentPage,size,user);

    }

    @ApiOperation(value = "分页查询用户")
    @GetMapping("/user/page/")
    public RespPageBean getPageUser(@RequestParam(defaultValue = "1") Integer currentPage,
                                      @RequestParam(defaultValue = "10")Integer size,
                                      TUser user){
        return userService.getUserByPage(currentPage,size,user);

    }
    @ApiOperation(value = "添加用户")
    @PostMapping("/user/insert")
    public RespBean addUser(@RequestBody TUser tUser){
      if (userService.getOne(new QueryWrapper<TUser>().eq("username",tUser.getUsername()))!=null){
          return RespBean.error("该用户名已存在！请重新添加");
      }
        if(tUser.getPassword()==null||tUser.getPassword()==""){
            tUser.setPassword("123123");
        }
        if(userService.save(tUser)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }
    @ApiOperation(value = "更新读者信息")
    @PutMapping("/user/update")
    public RespBean updateUser(@RequestBody TUser tUser, Authentication authentication){

        if(userService.updateById(tUser)){
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(tUser,null,
                            authentication.getAuthorities())
            );
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
    @ApiOperation(value = "更新用户头像")
    @PostMapping("/user/icon")
    public RespBean upload(@ApiParam(value="图片",required=true)@RequestPart("file") MultipartFile file, Integer id, Authentication authentication) {
        return userService.upload(file,id,authentication);
    }

    @ApiOperation(value = "更新读者密码")
    @PutMapping("/user/psw")
    public RespBean updateUserPassword(@RequestBody Map<String,Object> info){
        String oldPass=(String) info.get("oldPass");
        String pass=(String) info.get("pass");
        Integer userid=(Integer) info.get("userid");
        return userService.updatePassword(oldPass,pass,userid);
    }

    @ApiOperation(value = "重置读者密码")
    @PutMapping("/user/resetpsw")
    public RespBean resetPassword(@RequestParam(defaultValue = "123123") String psw,
                                  @RequestParam Integer id){
        TUser user=userService.getById(id);
        user.setPassword(psw);
        if (userService.updateById(user)){
            return RespBean.success("重置成功");
        }else{
            return RespBean.error("重置失败");
        }

    }



}

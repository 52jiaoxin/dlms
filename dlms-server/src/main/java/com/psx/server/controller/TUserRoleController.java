package com.psx.server.controller;


import com.psx.server.service.ITUserRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author psx
 * @since 2021-04-14
 */
@RestController
public class TUserRoleController {
    @Autowired
    private ITUserRoleService userRoleService;

    @ApiOperation(value = "获取用户角色")
    @GetMapping("/user/role")
    public String getRole(@RequestParam Integer userid){

        return userRoleService.getRole(userid);

    }
}

package com.psx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.psx.server.pojo.Admin;
import com.psx.server.pojo.RespBean;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author psx
 * @since 2021-03-24
 */
public interface IAdminService extends IService<Admin> {
    RespBean login(String username, String password, String code, HttpServletRequest request);
    public Admin getAdminByUsername(String username);
    RespBean register(String username, String password, String code, HttpServletRequest request);
}

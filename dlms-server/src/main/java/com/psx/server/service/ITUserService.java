package com.psx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.psx.server.pojo.TUser;
import com.psx.server.pojo.RespBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author psx
 * @since 2021-03-24
 */
public interface ITUserService extends IService<TUser> {
    RespBean login(String username, String password, String code, HttpServletRequest request);
    public TUser getAdminByUsername(String username);
    RespBean register(String username, String password, String code, HttpServletRequest request);

    List<TUser> getEmpList(String username);

    List<TUser> getReaderList(String username);
}

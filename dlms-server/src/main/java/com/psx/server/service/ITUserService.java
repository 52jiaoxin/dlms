package com.psx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.psx.server.pojo.RespBean;
import com.psx.server.pojo.RespPageBean;
import com.psx.server.pojo.TUser;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

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
    RespBean register(TUser user, String code, HttpServletRequest request);

    List<TUser> getEmpList(String username);

    List<TUser> getReaderList(String username);

    RespPageBean getUserByPage(Integer currentPage, Integer size, TUser user);

    RespBean updatePassword(String oldPass, String pass, Integer userid);

    List<TUser> getUserList(String keywords,Integer id);


    RespBean upload(MultipartFile file, Integer id, Authentication authentication);

    RespBean sendMail(String mail, HttpServletRequest request);
}

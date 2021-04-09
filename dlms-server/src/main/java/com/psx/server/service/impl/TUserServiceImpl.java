package com.psx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.psx.server.config.security.JwtTokenUtil;
import com.psx.server.mapper.TUserMapper;
import com.psx.server.pojo.TUser;
import com.psx.server.pojo.RespBean;
import com.psx.server.service.ITUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author psx
 * @date 2021/3/23 17:17
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;//用于加密，解密

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private TUserMapper userMapper;

    /*/**
    * Description:登陆之后返回token
    * @author: psx
    * @date: 2021/3/23 21:24
    * @paramType:[java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest]
    * @param:[username, password, request]
    * @return:com.psx.server.pojo.RespBean
    */
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request){
        //校验验证码
        String kaptcha = (String) request.getSession().getAttribute("kaptcha");
        if(code==null||!kaptcha.equalsIgnoreCase(code)){
            return RespBean.error("验证码输入错误！请重新输入");
        }
        //登录
       UserDetails userDetails = userDetailsService.loadUserByUsername(username);//获取UserDetails
       String newPassword=passwordEncoder.encode(userDetails.getPassword().trim());
       if (userDetails==null||!passwordEncoder.matches(password,newPassword)){
           return RespBean.error("用户名或密码错误");
       }
       if (!userDetails.isEnabled())
           return RespBean.error("账号被禁用，请联系管理员");
       //更新security登录用户对象
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new
                UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);//放到全局
        //生成token
       String token=jwtTokenUtil.generateToken(userDetails);
        Map<String,Object> map=new HashMap<>();
        map.put("token",token);
        map.put("tokenHead",tokenHead);
        return RespBean.success("登陆成功",map);
    }

    /*/**
    * Description:根据用户名获取用户
    * @author: psx
    * @date: 2021/3/23 21:34
    * @paramType:[java.lang.String]
    * @param:[username]
    * @return:Admin
    */
    @Override
    public TUser getAdminByUsername(String username){
            //查询表中姓名为username且账户没有被禁用的一个数据
            return userMapper.selectOne(new QueryWrapper<TUser>().eq("username",username).eq("enable",true));

    }

    @Override
    public RespBean register(String username, String password, String code, HttpServletRequest request) {
        //校验验证码
        String kaptcha = (String) request.getSession().getAttribute("kaptcha");
        if(code==null||!kaptcha.equalsIgnoreCase(code)){
            return RespBean.error("验证码输入错误！请重新输入");
        }
        TUser TUser =new TUser();
        TUser.setPassword(password);
        TUser.setUsername(username);
        if(userMapper.insertAdmin(TUser)==1){
            return RespBean.error("注册成功！");
        }
        return RespBean.error("注册有误！请重新注册！");
    }

    @Override
    public List<TUser> getUserList() {
        return userMapper.selectList(new QueryWrapper<TUser>());
    }
}
package com.psx.server.config.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.psx.server.pojo.RespBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 当未登录或者token失效时访问接口时，自定义的返回结果
 * @author psx
 * @date 2021/3/24 16:22
 */
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");//编码格式
        httpServletResponse.setContentType("application/json");//文本转为json格式
        PrintWriter out=httpServletResponse.getWriter();
        RespBean bean=RespBean.error("尚未登录");
        out.write(new ObjectMapper().writeValueAsString(bean));
        out.flush();
        out.close();
    }
}

package com.psx.server.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
*
 *   设置JWT登录授权过滤器
 *   @author psx
*    @date 2021/3/24 15:12
*/
public class JwtAuthencationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authHeader=httpServletRequest.getHeader(tokenHeader);
//        存在token
        if(authHeader!=null&&authHeader.startsWith(tokenHead)){
            String authToken=authHeader.substring(tokenHead.length());
            String username=jwtTokenUtil.getUserNameFromToken(authToken);
//            token存在但是没有登陆
            if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
//                登录
                UserDetails userDetails= userDetailsService.loadUserByUsername(username);
//                验证token是否有效，重新设置用户对象
                if(jwtTokenUtil.valuedToken(authToken,userDetails)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}

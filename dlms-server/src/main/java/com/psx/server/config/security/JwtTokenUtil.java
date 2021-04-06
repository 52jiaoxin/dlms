package com.psx.server.config.security;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken工具类
 * @author psx
 * @date 2021/3/23 15:19
 */
@Component
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME="sub";
    private static final String CLAIM_KEY_CREATED="created" ;
    @Value("${jwt.secret}")
    private String secret;//秘钥



    @Value("${jwt.expiration}")
    private Long expiration;//失效时间

   /*/**
   * Description: 根据用户信息生成Token
   * @author: psx
   * @date: 2021/3/23 15:48
   * @param:[userDetails]
   * @return:java.lang.String
   */
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>();//创建荷载
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }
    /*/**
    * Description:根据荷载生成JWT Token
    * @author: psx
    * @date: 2021/3/23 16:05
    * @paramType:[java.util.Map<java.lang.String,java.lang.Object>]
    * @param:[claims]
    * @return:java.lang.String
    */
    private String generateToken(Map<String,Object> claims){
        return Jwts.builder()//创建JWT令牌
                .setClaims(claims)//加载荷载
                .setExpiration(generateExpirationDate())//生效时间
                .signWith(SignatureAlgorithm.HS512,secret)   //签名
                .compact();
    }
    /*/**
    * Description:token失效时间
    * @author: psx
    * @date: 2021/3/23 16:05
    * @paramType:[]
    * @param:[]
    * @return:java.util.Date
    */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis()+expiration*1000);
    }
    /*/**
    * Description:从荷载中获取用户登录信息
    * @author: psx
    * @date: 2021/3/23 16:10
    * @paramType:[java.lang.String]
    * @param:[token]
    * @return:java.lang.String
    */
    public String getUserNameFromToken(String token){
        String userName;
        try{
            Claims claims=gteClaimsFromToken(token);
            userName=claims.getSubject();
        }catch (Exception e){
            userName=null;
        }
        return  userName;
    }
    /*/**
    * Description:从token中获取荷载
    * @author: psx
    * @date: 2021/3/23 16:11
    * @paramType:[java.lang.String]
    * @param:[token]
    * @return:io.jsonwebtoken.Claims
    */
    private Claims gteClaimsFromToken(String token) {
        Claims claims=null;
        try {
            claims=Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }
    /*/**
    * Description:判断token是否有效（包含token用户是不是当前登录用户和token是否过期）
    * @author: psx
    * @date: 2021/3/23 16:20
    * @paramType:[java.lang.String, org.springframework.security.core.userdetails.UserDetails]
    * @param:[token, userDetails]
    * @return:boolean
    */
    public boolean valuedToken(String token,UserDetails userDetails){
        String useName=getUserNameFromToken(token);
        return useName.equals(userDetails.getUsername())&& !isTokenExpired(token);
    }
    /*/**
    * Description:判断token是否过期
    * @author: psx
    * @date: 2021/3/23 16:21
    * @paramType:[java.lang.String]
    * @param:[token]
    * @return:boolean
    */
    private boolean isTokenExpired(String token) {
        Date expireDate=getExpiredDate(token);
        return expireDate.before(new Date());
    }
    /*/**
    * Description:根据token获得失效时间
    * @author: psx
    * @date: 2021/3/23 16:26
    * @paramType:[java.lang.String]
    * @param:[token]
    * @return:java.util.Date
    */
    private Date getExpiredDate(String token) {
        Claims claims=gteClaimsFromToken(token);
        return claims.getExpiration();
    }
    /*/**
    * Description:判断token是否可以被刷新
    * @author: psx
    * @date: 2021/3/23 16:28
    * @paramType:[java.lang.String]
    * @param:[token]
    * @return:boolean
    */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }
    /*/**
    * Description:刷新token
    * @author: psx
    * @date: 2021/3/23 16:52
    * @paramType:[java.lang.String]
    * @param:[token]
    * @return:java.lang.String
    */
    public String refreshToken(String token){
        Claims claims=gteClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

}

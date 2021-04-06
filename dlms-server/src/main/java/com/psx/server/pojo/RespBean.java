package com.psx.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共返回对象
 * @author psx
 * @date 2021/3/23 16:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object object;
    /*/**
    * Description:成功返回结果
    * @author: psx
    * @date: 2021/3/23 16:58
    * @paramType:[java.lang.String]
    * @param:[message]
    * @return:com.psx.server.pojo.RespBean
    */
    public static RespBean success(String message){
        return new RespBean(200,message,null);
    }
    /*/**
    * Description:成功返回结果
    * @author: psx
    * @date: 2021/3/23 16:58
    * @paramType:[java.lang.String, java.lang.Object]
    * @param:[message, o]
    * @return:com.psx.server.pojo.RespBean
    */
    public static RespBean success(String message, Object o){
        return new RespBean(200,message,o);
    }
    /*/**
    * Description:失败返回结果
    * @author: psx
    * @date: 2021/3/23 17:00
    * @paramType:[java.lang.String]
    * @param:[message]
    * @return:com.psx.server.pojo.RespBean
    */
    public static RespBean error(String message){
        return new RespBean(500,message,null);
    }
    /*/**
    * Description:失败返回结果
    * @author: psx
    * @date: 2021/3/23 17:00
    * @paramType:[java.lang.String, java.lang.Object]
    * @param:[message, o]
    * @return:com.psx.server.pojo.RespBean
    */
    public static RespBean error(String message, Object o){
        return new RespBean(500,message,o);
    }

}

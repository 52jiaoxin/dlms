package com.psx.server.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码接口
 * @author psx
 * @date 2021/3/29 9:54
 */

@RestController
public class KaptchaController {

    @Autowired
    private DefaultKaptcha myKaptcha;

    @ApiOperation(value = "验证码")
    @GetMapping(value = "/kaptcha",produces = "image/jpeg")
    public void setKaptcha(HttpServletRequest request, HttpServletResponse response){
    //定义图片响应头
        //定义response输出类型为image/jpeg类型
        response.setDateHeader ("Expires",0);
        // set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control","no-store,no-cache,must-revalidate");
        // set IE extended HTTP/1.1 no-cache headers (use addHeader) .
        response.addHeader("Cache-Control","post-check=0,pre-check=0");
        //set standard HTTP/1.0 no-cache header .
        response.setHeader ( "Pragma","no-cache ");
        // return a jpeg
        response.setContentType("image/jpeg");

    //生成验证码
        //获取验证码文本内容
        String imgText=myKaptcha.createText();
        System.out.println("内容"+imgText);
        //存入session
        request.getSession().setAttribute("kaptcha",imgText);
        //根据文本创建图形验证码
        BufferedImage image=myKaptcha.createImage(imgText);
        //输出流
        ServletOutputStream outputStream=null;
        try {
            outputStream=response.getOutputStream();
            //输出流输出图片并刷新输出流
            ImageIO.write(image,"jpg",outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream!=null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}

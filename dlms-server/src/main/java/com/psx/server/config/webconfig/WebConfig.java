package com.psx.server.config.webconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author psx
 * @date 2021/4/27 8:39
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //  /home/file/**为前端URL访问路径  后面 file:xxxx为本地磁盘映射
        //        registry.addResourceHandler("/img/**").addResourceLocations("file:C:" + uploadPath);
//        图片访问
        registry.addResourceHandler("/image/user/**").addResourceLocations("file:H://photo/user/");
        registry.addResourceHandler("/image/book/**").addResourceLocations("file:H://photo/book/");
//          Swagger访问
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/","classpath:/META-INF/resources/webjars/");
    }

}

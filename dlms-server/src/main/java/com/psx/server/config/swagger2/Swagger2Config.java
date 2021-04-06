package com.psx.server.config.swagger2;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Swagger2配置
 * @author psx
 * @date 2021/3/24 16:47
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRespApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //作用的包
                .apis(RequestHandlerSelectors.basePackage("com.psx.server.controller"))
                .paths(PathSelectors.any())
                //下面两个是为了添加头部请求
                .build()
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes());
    }
//设置API文档说明
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("基于SpringBoot的数字图书馆管理系统API")
                .description("基于SpringBoot的数字图书馆管理系统")
                //这里的端口就是主配置的端口
                .contact(new Contact("psx","http:localhost:8080/doc.html","2912508225@qq.com"))
                .version("1.0")
                .build();
    }

    private List<SecurityScheme> securitySchemes(){
//        设置请求头信息
        List<SecurityScheme> result=new ArrayList<>();
        SecurityScheme securityScheme=new ApiKey("Authorization","Authorization","Header");
        result.add(securityScheme);
        return result;
    }
    private List<SecurityContext> securityContexts(){
        //设置需要认证登录的路径
        List<SecurityContext> result=new ArrayList<>();
        result.add(getContextByPath("/hello/.*"));
        return result;
    }

    private SecurityContext getContextByPath(String s) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(s))

                .build();
    }

    /*/**
    * Description:默认授权
    * @author: psx
    * @date: 2021/3/28 10:52
    * @paramType:[]
    * @param:[]
    * @return:java.util.List<springfox.documentation.service.SecurityReference>
    */
    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result=new ArrayList<>();
        //授权范围
        AuthorizationScope authorizationScope=new AuthorizationScope("global","accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference( "Authorization" ,authorizationScopes));
        return result;
    }

}

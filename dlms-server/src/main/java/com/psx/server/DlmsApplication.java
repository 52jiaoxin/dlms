package com.psx.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * @author psx
 * @date 2021/3/22 14:56
 */
@SpringBootApplication
@MapperScan("com.psx.server.mapper")
public class DlmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DlmsApplication.class,args);
    }

}

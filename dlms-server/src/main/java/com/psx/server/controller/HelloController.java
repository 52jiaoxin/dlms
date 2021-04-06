package com.psx.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 *
 * @author psx
 * @date 2021/3/24 17:46
 */
@RestController
public class HelloController {
    @GetMapping("hello")
    public String hello(){
        return "hello";
    }
}

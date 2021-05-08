package com.psx.server.controller;

import com.psx.server.pojo.ChatMessage;
import com.psx.server.pojo.TUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

/**
 * @author psx
 * @date 2021/4/21 9:40
 */
@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @ApiOperation(value = "消息发送")
    @MessageMapping("/ws/chat")
    public void handleMessage(Authentication authentication, ChatMessage message){
        TUser user=(TUser) authentication.getPrincipal();
        message.setFrom(user.getUsername());
        message.setTime(LocalDateTime.now());
        System.out.println(message);
//        发送
        simpMessagingTemplate.convertAndSendToUser(message.getTo(),"/queue/chat",message);

    }
}

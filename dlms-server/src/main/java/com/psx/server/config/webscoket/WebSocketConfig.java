package com.psx.server.config.webscoket;

import com.psx.server.config.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocket配置类
 * @author psx
 * @date 2021/4/21 9:00
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    /*/**
    * Description:为了使网页可以通过websocket连上服务器，需要配置服务地址并指定使用socketJS
    * @author: psx
    * @date: 2021/4/21 9:11
    * @paramType:[org.springframework.web.socket.config.annotation.StompEndpointRegistry]
    * @param:[registry]
    * @return:void
    */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /*/**
        * Description:将
            addEndpoint：将ws/ep注册为stomp的端点，连接该端点就可以进行通信，并且支持socketJS
            setAllowedOrigins：允许跨域
            withSockJS：支持socketJS

         */
        registry.addEndpoint("/ws/ep").setAllowedOrigins("*").withSockJS();
    }

    /*/**
    * Description:输入通道配置，使用了JWT令牌需要配置
    * @author:
    * @date: 2021/4/21 9:21
    * @paramType:[org.springframework.messaging.simp.config.ChannelRegistration]
    * @param:[registration]
    * @return:void
    */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor stompHeaderAccessor = MessageHeaderAccessor
                        .getAccessor(message, StompHeaderAccessor.class);
//                判断是否为连接
                if (StompCommand.CONNECT.equals(stompHeaderAccessor.getCommand())){
                    String token = stompHeaderAccessor.getFirstNativeHeader("Auth-Token");
                    if (!StringUtils.isEmpty(token)){
                        String authToken=token.substring(tokenHead.length());
                        String username=jwtTokenUtil.getUserNameFromToken(authToken);
                        //token中存在用户名
                        if(!StringUtils.isEmpty(username)){
                            //登录
                            UserDetails userDetails=userDetailsService.loadUserByUsername(username);
                            //token有效
                            if(jwtTokenUtil.valuedToken(authToken,userDetails)){
                                UsernamePasswordAuthenticationToken authenticationToken=
                                    new UsernamePasswordAuthenticationToken(userDetails,null,
                                            userDetails.getAuthorities());
                                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                                stompHeaderAccessor.setUser(authenticationToken);
                            }
                        }
                    }
                }
                return message;
            }

        });
    }

    /*/**
    * Description:配置消息代理
    * @author: psx
    * @date: 2021/4/21 9:17
    * @paramType:[org.springframework.messaging.simp.config.MessageBrokerRegistry]
    * @param:[registry]
    * @return:void
    */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        配置代理域，可以配置多个，代理目的地的前缀为/queue，可以在配置域向客服端推送消息
        registry.enableSimpleBroker("/queue");
    }
}

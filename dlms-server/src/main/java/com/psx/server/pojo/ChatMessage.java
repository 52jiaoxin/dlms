package com.psx.server.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 消息
 * @author psx
 * @date 2021/4/21 9:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChatMessage {

//      来自
    private String from;
    //      来自
    private String fromNickname;
//    去往
    private String to;
//    内容
    private String content;
//    时间
    private LocalDateTime time;
//    是不是本人
    private boolean notSelf;



}

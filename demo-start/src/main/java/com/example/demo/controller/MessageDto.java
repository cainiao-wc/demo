package com.example.demo.controller;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author wuchang006
 * date 2022-09-16:16
 * note: 消息体
 */

@Data
@Builder
public class MessageDto {

    private String msgtype;
    private Text text;

    @Data
    public static class Text {
        private String content;
    }
}

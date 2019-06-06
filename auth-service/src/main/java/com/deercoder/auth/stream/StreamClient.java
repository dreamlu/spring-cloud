package com.deercoder.auth.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 自定义队列消息
 */
public interface StreamClient {

    String OUTPUT = "countPerOut";

    // 接收消息、入口
    //@Input("countPerInput")
    //SubscribableChannel input();

    // 发送消息、出口
    @Output(OUTPUT)
    MessageChannel output();
}
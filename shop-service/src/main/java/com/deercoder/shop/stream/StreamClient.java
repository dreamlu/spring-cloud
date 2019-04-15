package com.deercoder.shop.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 自定义队列消息
 */
public interface StreamClient {

    String INPUT = "countPerInput";

    // 接收消息、入口
    @Input(INPUT)
    SubscribableChannel input();

    // 发送消息、出口
    //@Output("countPerOut")
    //MessageChannel output();
}
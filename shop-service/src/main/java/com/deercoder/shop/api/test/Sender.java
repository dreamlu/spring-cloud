package com.deercoder.shop.api.test;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
//public class Sender {
//
//	@Autowired
//	private RabbitTemplate rabbitTemplate;
//
//	public void send() {
//		String context = "test " + new Date();
//		System.out.println("发送信息 : " + context);
//		rabbitTemplate.convertAndSend("test", context);
//	}
//
//}
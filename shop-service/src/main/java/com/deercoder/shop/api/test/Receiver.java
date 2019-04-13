package com.deercoder.shop.api.test;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component

public class Receiver {

	//@RabbitHandler
	@RabbitListener(queues = "hello2")
	public void receive(String hello) {
		System.out.println("接收信息 : " + hello);
	}

}
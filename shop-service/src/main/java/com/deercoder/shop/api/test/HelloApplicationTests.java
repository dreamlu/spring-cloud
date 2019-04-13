package com.deercoder.shop.api.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@EnableRabbit
public class HelloApplicationTests {

	@Autowired
	private Sender sender;

	@Test
	public void hello() {
		sender.send();
	}

}
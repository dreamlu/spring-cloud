package com.deercoder.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.deercoder.commons.util.Util.randNum;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApplicationTests.class)
public class CommonApplicationTests {

	@Test
	public void test() {
//		System.out.println("短信发送结果: " + new SmsController().send("15869190407"));
	}

	// 随机数测试
	@Test
	public void testCode() {
		for (int j = 0; j < 100; j++) {
//			System.out.println((int)((Math.random()*9+1)*10e5));
			System.out.println(randNum(6));
		}
	}
}

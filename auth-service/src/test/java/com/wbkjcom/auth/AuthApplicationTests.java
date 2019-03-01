package com.wbkjcom.auth;

import com.wbkjcom.auth.model.Admin;
import com.wbkjcom.auth.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthApplicationTests {

	@Autowired
	private AdminService adminService;

	@Test
	public void contextLoads() {
		System.out.println("注册结果:"+adminService.register(new Admin("admin", "123456")));
	}

	@Test
	public void login(){
		System.out.println("登录结果:"+adminService.login(new Admin("admin", "123456")));
	}
}

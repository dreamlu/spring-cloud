package com.deercoder.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient // 替代@EnableEurekaClient
@EnableFeignClients
public class CommonApplication {
	public static void main(String[] args) {
		SpringApplication.run(CommonApplication.class, args);
	}
}

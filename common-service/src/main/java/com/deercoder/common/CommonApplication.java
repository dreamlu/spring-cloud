package com.deercoder.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // 替代@EnableEurekaClient
public class CommonApplication {
	public static void main(String[] args) {
		SpringApplication.run(CommonApplication.class, args);
	}
}

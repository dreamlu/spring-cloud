package com.deercoder.apigateway;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringCloudApplication
//@EnableFeignClients//开启扫描Spring Cloud Feign客户端的功能
@EnableDiscoveryClient    //网关异常：com.netflix.zuul.exception.ZuulException: Forwarding error
public class ApiGatewayApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ApiGatewayApplication.class).run();//web(WebApplicationType.NONE).run(args);
	}

	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}
}

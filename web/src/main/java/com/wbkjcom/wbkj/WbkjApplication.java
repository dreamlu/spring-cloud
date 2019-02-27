package com.wbkjcom.wbkj;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients//开启扫描Spring Cloud Feign客户端的功能
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class WbkjApplication {//extends SpringBootServletInitializer

//	@Override protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(wbkjApplication.class);
//	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(WbkjApplication.class).run();//.web(WebApplicationType.NONE).run(args);
	}
}

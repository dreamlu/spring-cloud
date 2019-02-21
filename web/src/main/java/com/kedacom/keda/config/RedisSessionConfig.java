package com.kedacom.keda.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 开启spring session支持
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {
    //Spring Session替换了默认的tomcat httpSession
}

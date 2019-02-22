package com.wbkjcom.user.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 开启spring session支持
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {
    // Spring Session替换了默认的tomcat httpSession,测试共享session，
    // 访问http://localhost:8771/test/cookie?browser=chrome向web服务session中添加session key为 browser
    // 访问user服务http://localhost:8767/user/test/cookie 可看到结果 【存在session，browser=chrome】
    // 配置步骤
    // 1、pom引入依赖 spring-session-data-redis
    // 2、配置类 开启Redis Http Session
    // 3、配置文件application.yml，配置连接的redis信息
    // 4、编写测试Controller，观察Spring Session的特性
}

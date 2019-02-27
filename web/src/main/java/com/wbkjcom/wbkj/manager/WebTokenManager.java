//package com.wbkjcom.wbkj.manager;
//
//import com.wbkjcom.commons.manager.impl.RedisTokenManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
///**
// * com.wbkjcom.apigateway.manager.impl
// */
//@Component
//public class WebTokenManager extends RedisTokenManager {
//
////	private RedisTemplate<Long, String> redis;
//
////	@Autowired
////	public WebTokenManager(@Qualifier("stringRedisTemplate") RedisTemplate<Long, String> redis) {
////		super(redis);
////	}
//
//	@Autowired
//    public void setRedis (@Qualifier("stringRedisTemplate") RedisTemplate redis) {
////        super(redis);
//        // 泛型设置成 Long 后必须更改对应的序列 化方案
////        redis.setKeySerializer (new JdkSerializationRedisSerializer());
//    }
//}

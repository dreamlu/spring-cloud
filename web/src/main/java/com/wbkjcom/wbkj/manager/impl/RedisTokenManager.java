package com.wbkjcom.wbkj.manager.impl;

///**
// * com.wbkjcom.apigateway.manager.impl
// */
//@Component
//public class RedisTokenManager implements TokenManager {
//
//    private RedisTemplate<Long, String> redis;
//
//    @Autowired
//    public void setRedis (@Qualifier("stringRedisTemplate") RedisTemplate redis) {
//        this.redis = redis;
//        // 泛型设置成 Long 后必须更改对应的序列 化方案
//        redis.setKeySerializer (new JdkSerializationRedisSerializer());
//    }
//
//    public TokenModel createToken (long userId) {
//        return  new TokenUtil(redis).createToken(userId);
//    }
//
//    public TokenModel getToken (String authentication) {
//        return  new TokenUtil(redis).getToken(authentication);
//    }
//
//    public boolean checkToken (TokenModel model) {
//        return  new TokenUtil(redis).checkToken(model);
//    }
//
//    public void deleteToken (long userId) {
//        redis.delete (userId);
//    }
//}

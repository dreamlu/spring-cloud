package com.deercoder.shop.config;

import com.deercoder.commons.util.sql.CrudUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Repository;

/**
 * 开启redis通用缓存
 * 此处为通用缓存(业务查询多可用)
 * 具体业务逻辑可根据源码改造
 * 源码参考 {@link com.deercoder.commons.util.sql.CrudUtil}
 * 缓存通用失效时间{@link com.deercoder.commons.lib.Constants}
 *
 * @author dreamlu
 * @date 2019/04/17
 */
@Repository
public class RedisConfig {

	@Autowired
	public void setRedis(RedisConnectionFactory redisConnectionFactory) {
		CrudUtil.setCacheManager(redisConnectionFactory);
	}
}
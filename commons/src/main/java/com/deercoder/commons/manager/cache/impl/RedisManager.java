package com.deercoder.commons.manager.cache.impl;

import com.deercoder.commons.manager.cache.CacheManager;
import com.deercoder.commons.model.CacheModel;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * des: 缓存redis实现
 */
public class RedisManager implements CacheManager {

	private RedisTemplate<Object, CacheModel> redis;

	public RedisManager(RedisConnectionFactory redisConnectionFactory) {

		RedisTemplate<Object, CacheModel> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);

		// 使用Jackson2JsonRedisSerialize 替换默认序列化
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

		// 设置value的序列化规则和 key的序列化规则
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.afterPropertiesSet();

		this.redis = redisTemplate;
	}

	@Override
	public void set(Object key, CacheModel value) {

		redis.opsForValue().set(key, value);
		if (value.getTime() != null) {
			redis.expire(key, value.getTime(), TimeUnit.MINUTES);
			//redis.opsForValue().set(key, value, value.getTime(), TimeUnit.MINUTES);// 方法时间设置失效
		}

	}

	@Override
	public CacheModel get(Object key) {
		return redis.opsForValue().get(key);
	}

	@Override
	public boolean delete(Object key) {

		return redis.delete(key);
	}

	@Override
	public Long deletePrex(Object keyPrex) {

		Set<Object> keys = redis.keys(keyPrex);
		return redis.delete(keys);
	}


	@Override
	public CacheModel check(Object key) {

		if (key == null) {
			return null;
		}
		CacheModel value = redis.opsForValue().get(key);
		if (value != null) {
			return null;
		}
		// 验证成功, 重新设置过期时间，刷新时间
		redis.expire(key, value.getTime(), TimeUnit.MINUTES);
		return value;
	}
}

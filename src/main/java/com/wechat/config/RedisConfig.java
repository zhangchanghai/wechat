package com.wechat.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration  

public class RedisConfig extends CachingConfigurerSupport {
	 @Bean
	    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
	        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
	        redisTemplate.setConnectionFactory(redisConnectionFactory);
	        redisTemplate.setKeySerializer(new StringRedisSerializer());
	        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);  
	        ObjectMapper om = new ObjectMapper();  
	        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);  
	        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);  
	        jackson2JsonRedisSerializer.setObjectMapper(om);  
	  
	        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);  
	        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);  
	        redisTemplate.afterPropertiesSet();  
	  
	        return redisTemplate;  
	    }
}
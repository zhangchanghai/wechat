package com.wechat.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration  

public class RedisConfig extends CachingConfigurerSupport {
    @Bean
    public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate) {
       CacheManager cacheManager = new RedisCacheManager(redisTemplate);
       return cacheManager;
    }

    @Bean  
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory connectionFactory) {  
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();  
        template.setConnectionFactory(connectionFactory);  
        template.afterPropertiesSet();  
        return template;  
    }  
}
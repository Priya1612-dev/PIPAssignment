//package com.EmployeeOrgManagement.Utils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.data.redis.connection.RedisConnectionFactory;
////import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
////import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
////import org.springframework.data.redis.core.RedisTemplate;
//
////@Configuration
//public class RedisConfig {
////    @Bean
////    public JedisConnectionFactory redisConnectionFactory() {
////        return new JedisConnectionFactory();
////    }
////    @Bean
////    RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
////
////        RedisTemplate<String, String> template = new RedisTemplate<>();
////        template.setConnectionFactory(redisConnectionFactory);
////        return template;
////    }
////    @Bean(name = "factory")
////    JedisConnectionFactory jedisConnectionFactory() {
////        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
////        return jedisConFactory;
////    }
////
////    @Bean(name = "common")
////    @Autowired
////    public RedisTemplate<String, Object> redisTemplate(@Qualifier("factory") JedisConnectionFactory jedisConnectionFactory) {
////        RedisTemplate<String, Object> template = new RedisTemplate<>();
////        template.setConnectionFactory(jedisConnectionFactory);
////        return template;
////    }
//    }

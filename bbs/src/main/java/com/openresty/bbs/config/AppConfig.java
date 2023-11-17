package com.openresty.bbs.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration    // 创建一个配置类， 添加@Configuration注解声明为配置类
@MapperScan(basePackages = {"com.openresty.dao.mapper"})
@ComponentScan(basePackages = {"com.openresty.dao.service", "com.openresty.dao.service.impl"})
public class AppConfig {

    // 方法上加上@bean，该方法用于创建实例并返回，该实例创建后会交给spring管理，方法名建议与实例名相同（首字母小写）。注：实例类不需要加任何注解
    /*
    @Bean
    public IRedisService redisService() {
        return new RedisServiceImpl(); // Replace with your actual implementation
    }
   */

    /*
    @Bean
    public ITopicService topicService() {
        return new TopicServiceImpl(); // Replace with your actual implementation
    }
   */



    /**
     * redis template.
     * @param factory factory
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

}
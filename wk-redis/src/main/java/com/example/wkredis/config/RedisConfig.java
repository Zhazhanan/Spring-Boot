package com.example.wkredis.config;

import com.example.wkredis.module.SubscribeListener;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;

/**
 * @author WangKun
 * @create 2019-01-03
 * @desc
 **/
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

//    @Bean(name = "lockScript")
//    public RedisScript<Long> lockScript() {
//        String script = "if redis.call('setNx',KEYS[1],ARGV[1]) then return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end";
//        return RedisScript.of(script, Long.class);
//    }
//
//    @Bean(name = "releaseLockScript")
//    public RedisScript<Long> releaseLockScript() {
//        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
//        return RedisScript.of(script, Long.class);
//    }

    @Override
    public KeyGenerator keyGenerator() {
        return (Object target, Method method, Object... params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());

            if (params.length > 0) {
                sb.append("[");
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                sb.append("]");
            }
            return sb.toString();
        };
    }

    /**springboot2.0*/
//    @Bean
//    public RedisCacheConfiguration redisCacheConfiguration() {
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .serializeValuesWith(
//                        RedisSerializationContext
//                                .SerializationPair
//                                .fromSerializer(jackson2JsonRedisSerializer)
//                ).entryTtl(Duration.ofMinutes(30))
//                .disableCachingNullValues();
//
//        return redisCacheConfiguration;
//    }

    /**
     * springboot1.5
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        //设置缓存过期时间C
        //rcm.setDefaultExpiration(60);//秒
        return rcm;
    }

    /**
     * springboot2.0
     */
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory factory, RedisCacheConfiguration config) {
//        return RedisCacheManager.builder(factory)
//                .cacheDefaults(config)
//                .transactionAware()
//                .build();
//    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        ObjectMapper om = new ObjectMapper();
        // 设置任何字段可见
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 设置不是final的属性可以转换
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        // 用于value序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 用于key序列化
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();

        /**
         * 普通键值对设置序列化对象
         */
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        /**
         * hash设置序列化对象
         */
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.afterPropertiesSet();
        redisTemplate.setEnableTransactionSupport(true);

        return redisTemplate;
    }

    /**
     * @describe: 监听主题
     * @author: WangKun
     * @date: 2019-03-01 04:55
     **/
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory factory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        /**
         * 添加订阅者监听类，数量不限.PatternTopic定义监听主题,这里监听dj主题
         */
        container.addMessageListener(new SubscribeListener(), new PatternTopic("dj"));
        return container;

    }

}

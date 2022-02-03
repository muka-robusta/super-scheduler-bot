package me.vegura.superscheduler.config;

import me.vegura.superscheduler.domain.FloatingEvent;
import me.vegura.superscheduler.domain.Reminder;
import me.vegura.superscheduler.listeners.KeyExpiredListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import javax.annotation.Resource;

@Configuration
@EnableCaching
public class RedisConfig {

    @Value("${custom.redis.host}")
    private String host;

    @Value("${custom.redis.port}")
    private int port;

    @Resource
    private KeyExpiredListener keyExpirationListener;

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        final RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, FloatingEvent> eventRedisTemplate() {
        RedisTemplate<String, FloatingEvent> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }

    @Bean
    public RedisTemplate<String, Reminder> reminderRedisTemplate() {
        RedisTemplate<String, Reminder> reminderRedisTemplate = new RedisTemplate<>();
        reminderRedisTemplate.setConnectionFactory(redisConnectionFactory());
        return reminderRedisTemplate;
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {
        final RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory());
        redisMessageListenerContainer.addMessageListener(keyExpirationListener, new PatternTopic("__keyevent@*__:expired"));
        return redisMessageListenerContainer;
    }
}

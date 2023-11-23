package me.vegura.superscheduler.repository.redis.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.vegura.superscheduler.domain.Notification;
import me.vegura.superscheduler.repository.redis.CacheNotificationRepository;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor @Slf4j
public class CacheNotificationRepositoryImpl implements CacheNotificationRepository {

    private final RedisTemplate<String, String> notificationRedisTemplate;
    private HashOperations<String, String, String> redisOperations;

    @PostConstruct
    public void setUpRedisHashOperations() {
        redisOperations = notificationRedisTemplate.opsForHash();
    }

    @Override
    public void createNotification(Notification notification) {
        String cacheKey = generateCacheKey(notification);
        String eventHashKey = notification.getEventId().toString();
        final long ttlValue = generateTtlValue(notification.getNotificationTime());
        log.info("Creating notification with cache key -> {}", cacheKey);

        redisOperations.put(cacheKey, eventHashKey, cacheKey);
        notificationRedisTemplate.expire(cacheKey, ttlValue, TimeUnit.MILLISECONDS);
    }

    @Override
    public void deleteNotification(Notification notification) {
        String cacheKey = generateCacheKey(notification);
        String eventHashKey = notification.getEventId().toString();
        log.info("Deleting notification with cache key = {}", cacheKey);

        redisOperations.delete(cacheKey, eventHashKey);
    }

    private long generateTtlValue(Instant notificationTime) {
        return notificationTime.toEpochMilli() - Instant.now().toEpochMilli();
    }

    private String generateCacheKey(Notification notification) {
        try {
            return new ObjectMapper().writeValueAsString(notification);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return notification.getNotificationTime() + "__" + notification.getEventId();
        }
    }
}

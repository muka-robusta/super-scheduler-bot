package me.vegura.superscheduler.repository.redis.impl;

import lombok.RequiredArgsConstructor;
import me.vegura.superscheduler.domain.FloatingEvent;
import me.vegura.superscheduler.repository.redis.FloatingEventRepository;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FloatingEventRepositoryImpl implements FloatingEventRepository {

    private static final String REDIS_ENTITY = "floating_event";

    private final RedisTemplate<String, FloatingEvent> redisTemplate;
    private HashOperations<String, Instant, FloatingEvent> hashOperations;

    @PostConstruct
    public void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Optional<FloatingEvent> findEventByTime(Instant time) {
        return Optional.ofNullable(hashOperations.get(REDIS_ENTITY, time));
    }

    @Override
    public void deleteEventByTime(Instant time) {
        hashOperations.delete(REDIS_ENTITY, time);
    }

    @Override
    public void saveEvent(FloatingEvent event) {
        hashOperations.put(REDIS_ENTITY, event.getTime(), event);
    }

    @Override
    public void updateEvent(FloatingEvent event) {
        this.saveEvent(event);
    }
}

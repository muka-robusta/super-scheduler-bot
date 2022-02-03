package me.vegura.superscheduler.repository.redis.impl;

import lombok.RequiredArgsConstructor;
import me.vegura.superscheduler.domain.Reminder;
import me.vegura.superscheduler.repository.redis.ReminderRepository;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class ReminderRepositoryImpl implements ReminderRepository {

    private static final String REDIS_ENTITY_PREFIX = "REMINDER";
    private static final String REDIS_EVENT_PREFIX = "EVENT";

    private final RedisTemplate<String, Reminder> reminderRedisTemplate;
    private HashOperations<String, String, Reminder> hashOperations;

    @PostConstruct
    public void init() {
        hashOperations = reminderRedisTemplate.opsForHash();
    }

    @Override
    public void addReminder(Reminder reminder, Integer ttlValue, TimeUnit timeUnit) {
        String hashEntityKey = generateHashEntityKey(reminder);
        String timeEventKey =  generateHashEventKey(reminder);
        hashOperations.put(hashEntityKey, timeEventKey, reminder);
        reminderRedisTemplate.expire(hashEntityKey, ttlValue, timeUnit);
    }

    @Override
    public void deleteReminder(Reminder reminder) {
        hashOperations.delete(generateHashEntityKey(reminder), generateHashEventKey(reminder));
    }

    private static String generateHashEntityKey(Reminder reminder) {
        return REDIS_ENTITY_PREFIX + "_" + reminder.getRemindTime();
    }

    private static String generateHashEventKey(Reminder reminder) {
        return REDIS_EVENT_PREFIX + "_" + reminder.getEvent().getId();
    }
}

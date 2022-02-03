package me.vegura.superscheduler.domain;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.time.Instant;

@Data
public class Reminder {
    private Event event;
    private Instant remindTime;
    private String chatId;
}

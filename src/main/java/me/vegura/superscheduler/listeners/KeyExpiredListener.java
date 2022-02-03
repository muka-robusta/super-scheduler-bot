package me.vegura.superscheduler.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.vegura.superscheduler.repository.redis.ReminderRepository;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPubSub;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Slf4j
public class KeyExpiredListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String key = new String(message.getBody());
        log.info("Expired key {}: {}", key, message.getBody());
    }
}

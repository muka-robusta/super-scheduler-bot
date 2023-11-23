package me.vegura.superscheduler.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.vegura.superscheduler.processor.EventNotificationProcessor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor @Slf4j
public class KeyExpiredListener implements MessageListener {

    private final EventNotificationProcessor processor;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String body = new String(message.getBody(), StandardCharsets.UTF_8);
        final String[] els = body.split("\\$");
        log.info("Expired key {} <==> {} <==> {}", new String(message.getChannel()), body, new String(pattern));
    }
}

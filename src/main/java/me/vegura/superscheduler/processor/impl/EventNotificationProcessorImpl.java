package me.vegura.superscheduler.processor.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.vegura.superscheduler.domain.Notification;
import me.vegura.superscheduler.processor.EventNotificationProcessor;
import org.springframework.stereotype.Component;

@Component
public class EventNotificationProcessorImpl implements EventNotificationProcessor {

    @Override
    public void processNotification(String notificationCacheString) {
        try {
            final Notification notification = new ObjectMapper()
                    .readValue(notificationCacheString, Notification.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

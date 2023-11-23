package me.vegura.superscheduler.processor;

import org.springframework.stereotype.Component;

public interface EventNotificationProcessor {
    void processNotification(String notificationCacheString);
}

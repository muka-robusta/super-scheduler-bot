package me.vegura.superscheduler.repository.redis;

import me.vegura.superscheduler.domain.Event;
import me.vegura.superscheduler.domain.Notification;

import java.util.concurrent.TimeUnit;

public interface CacheNotificationRepository {
    void createNotification(Notification notification);
    void deleteNotification(Notification notification);
}

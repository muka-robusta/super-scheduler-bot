package me.vegura.superscheduler.service;

import me.vegura.superscheduler.domain.Event;

import java.time.Instant;

public interface NotificationService {
    void createNotification(Event event, Instant notificationTime, String chatId);
    void remindIn1Minute(Event event, String chatId);

}

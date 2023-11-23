package me.vegura.superscheduler.service.impl;

import lombok.RequiredArgsConstructor;
import me.vegura.superscheduler.domain.Event;
import me.vegura.superscheduler.domain.Notification;
import me.vegura.superscheduler.repository.postgres.EventRepository;
import me.vegura.superscheduler.repository.redis.CacheNotificationRepository;
import me.vegura.superscheduler.service.NotificationService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final CacheNotificationRepository cacheNotificationRepository;
    private final EventRepository eventRepository;

    @Override
    public void createNotification(Event event, Instant notificationTime, String chatId) {
        final Optional<Event> maybeEvent = eventRepository.findByTime(event.getTime());
        if (maybeEvent.isEmpty())
            eventRepository.save(event);

        final Notification notification = new Notification()
                .setEventId(event.getId())
                .setNotificationTime(notificationTime);
        cacheNotificationRepository.createNotification(notification);
    }

    @Override
    public void remindIn1Minute(Event event, String chatId) {
        final Instant remindTime = Instant.now().plusSeconds(60);
        createNotification(event, remindTime, chatId);
    }
}

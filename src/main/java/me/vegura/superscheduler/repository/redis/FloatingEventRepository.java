package me.vegura.superscheduler.repository.redis;

import me.vegura.superscheduler.domain.FloatingEvent;

import java.time.Instant;
import java.util.Optional;

public interface FloatingEventRepository {
    Optional<FloatingEvent> findEventByTime(Instant time);
    void deleteEventByTime(Instant time);
    void saveEvent(FloatingEvent event);
    void updateEvent(FloatingEvent event);
}

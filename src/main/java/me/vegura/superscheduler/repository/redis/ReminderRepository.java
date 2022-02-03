package me.vegura.superscheduler.repository.redis;

import me.vegura.superscheduler.domain.Reminder;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public interface ReminderRepository {
    void addReminder(Reminder reminder, Integer ttlValue, TimeUnit timeUnit);
    void deleteReminder(Reminder reminder);
}

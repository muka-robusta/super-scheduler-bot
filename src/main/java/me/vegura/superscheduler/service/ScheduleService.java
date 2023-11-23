package me.vegura.superscheduler.service;

import me.vegura.superscheduler.domain.ScheduleEvent;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.Set;

public interface ScheduleService {
    Set<ScheduleEvent> findAll();
    Set<ScheduleEvent> findAllByDayOfWeek(DayOfWeek day);
    void saveDaySchedule(Collection<ScheduleEvent> eventCollection, DayOfWeek day);
}

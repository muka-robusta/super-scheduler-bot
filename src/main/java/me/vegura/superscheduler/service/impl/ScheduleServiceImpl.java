package me.vegura.superscheduler.service.impl;

import lombok.RequiredArgsConstructor;
import me.vegura.superscheduler.domain.ScheduleEvent;
import me.vegura.superscheduler.domain.Subject;
import me.vegura.superscheduler.repository.postgres.ScheduleEventRepository;
import me.vegura.superscheduler.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleEventRepository scheduleRepository;

    @Override
    public Set<ScheduleEvent> findAll() {
        return new HashSet<>(scheduleRepository.findAll());
    }

    @Override
    public Set<ScheduleEvent> findAllByDayOfWeek(DayOfWeek day) {
        final Collection<ScheduleEvent> byDayOfWeek = scheduleRepository.findByDayOfWeek(day);

        if (day == DayOfWeek.TUESDAY) {
            return Set.of(
                    new ScheduleEvent()
                            .setTime(LocalTime.of(10, 25))
                            .setDayOfWeek(DayOfWeek.TUESDAY)
                            .setSubject(new Subject().setName("тпр").setTutorName("Зайченко Юрий Петрович"))
                            .setOrderValue(1)
            );
        }
        return null;
    }

    @Override
    public void saveDaySchedule(Collection<ScheduleEvent> eventCollection, DayOfWeek day) {

    }
}

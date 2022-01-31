package me.vegura.superscheduler.service.impl;

import me.vegura.superscheduler.domain.ScheduleEvent;
import me.vegura.superscheduler.domain.Subject;
import me.vegura.superscheduler.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Override
    public Set<ScheduleEvent> findAll() {
        return null;
    }

    @Override
    public Set<ScheduleEvent> findAllByDayOfWeek(DayOfWeek day) {
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
}

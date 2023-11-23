package me.vegura.superscheduler.repository.postgres;

import me.vegura.superscheduler.domain.ScheduleEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.Collection;

public interface ScheduleEventRepository extends JpaRepository<ScheduleEvent, Long> {
    Collection<ScheduleEvent> findByDayOfWeek(DayOfWeek day);
}

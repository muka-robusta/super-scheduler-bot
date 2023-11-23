package me.vegura.superscheduler.domain;

//import lombok.Builder;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.DayOfWeek;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Data @Accessors(chain = true)
@Entity @Table(name = "schedule_events")
public class ScheduleEvent extends AbstractEntity {

    @ManyToOne @JoinColumn(name = "subject_id")
    private Subject subject;
    private Integer orderValue;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(name = "event_time")
    private LocalTime time;
}

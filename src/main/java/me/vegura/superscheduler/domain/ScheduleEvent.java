package me.vegura.superscheduler.domain;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

//import javax.persistence.Entity;
//import javax.persistence.ManyToOne;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.GregorianCalendar;

@Data @Accessors(chain = true)
//@Entity
public class ScheduleEvent extends AbstractEntity {

//    @ManyToOne
    private Subject subject;
    private Integer orderValue;
    private DayOfWeek dayOfWeek;
    private LocalTime time;
}

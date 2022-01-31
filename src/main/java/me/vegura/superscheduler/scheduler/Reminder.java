package me.vegura.superscheduler.scheduler;

import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

@Component
public class Reminder {

    private SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    public void remindEvent() {

    }
}

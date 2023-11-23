package me.vegura.superscheduler.converters;

import lombok.RequiredArgsConstructor;
import me.vegura.superscheduler.domain.ScheduleEvent;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScheduleWeekTextConverter implements TextConverter<Set<ScheduleEvent>>{

    private final ScheduleDayTextConverter dayTextConverter;

    @Override
    public String convert(Set<ScheduleEvent> scheduleEvents) {
        final Map<DayOfWeek, List<ScheduleEvent>> scheduleByDayOfWeek = scheduleEvents.stream()
                .collect(Collectors.groupingBy(ScheduleEvent::getDayOfWeek));

        final String messageHeading = composeWeekHeading();

        return Arrays.stream(DayOfWeek.values())
                .map(scheduleByDayOfWeek::get)
                .map(dayTextConverter::convert)
                .reduce(messageHeading, String::concat);
    }

    private static String composeWeekHeading() {
        return "\t<b>Week schedule</b>\n\n";
    }
}

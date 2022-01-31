package me.vegura.superscheduler.converters;

import lombok.RequiredArgsConstructor;
import me.vegura.superscheduler.domain.ScheduleEvent;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ScheduleDayTextConverter implements TextConverter<Set<ScheduleEvent>> {

    private static final String EMPTY_DAY_SCHEDULE_TEXT = "Schedule records is empty";

    private final ScheduleEventTextConverter singleEventConverter;

    @Override
    public String convert(Set<ScheduleEvent> dayScheduleSet) {
        final Optional<ScheduleEvent> maybeFirstEvent = dayScheduleSet.stream().findFirst();
        if (maybeFirstEvent.isEmpty())
            return EMPTY_DAY_SCHEDULE_TEXT;

        String dayOfWeek = maybeFirstEvent.get()
                .getDayOfWeek()
                .toString();
        final String messageHeading = composeHeading(dayOfWeek);

        return dayScheduleSet.stream()
                .sorted(Comparator.comparingInt(ScheduleEvent::getOrderValue))
                .map(singleEventConverter::convert)
                .reduce(messageHeading, String::concat);
    }

    private String composeHeading(String dayOfWeek) {
        return "\t<b>" + dayOfWeek + "</b>\n";
    }
}

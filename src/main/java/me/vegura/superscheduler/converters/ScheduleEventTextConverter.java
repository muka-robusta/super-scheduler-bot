package me.vegura.superscheduler.converters;

import me.vegura.superscheduler.domain.ScheduleEvent;
import me.vegura.superscheduler.domain.Subject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduleEventTextConverter implements TextConverter<ScheduleEvent> {

    @Override
    public String convert(ScheduleEvent schedule) {
        final StringBuilder convertedText = new StringBuilder();

        putOrder(convertedText, schedule.getOrderValue());
        putTime(convertedText, schedule.getTime());
        putSubject(convertedText, schedule.getSubject());
        putTutorName(convertedText, schedule.getSubject());
        putEndLine(convertedText);
        putDescription(convertedText, schedule.getSubject());
        putEndLine(convertedText);

        return convertedText.toString();
    }

    private void putOrder(StringBuilder message, Integer order) {
        message.append("[")
                .append(order)
                .append("]");
    }

    private void putTime(StringBuilder message, LocalTime eventTime) {
        message.append("[")
                .append(eventTime.format(DateTimeFormatter.ofPattern("HH:mm")))
                .append("] ");
    }

    private void putSubject(StringBuilder message, Subject subject) {
        message.append("<b>")
                .append(subject.getName())
                .append("</b> ");
    }

    private void putTutorName(StringBuilder message, Subject subject) {
        message.append(" - <i>")
                .append(subject.getTutorName())
                .append("</i>");
    }

    private void putDescription(StringBuilder message, Subject subject) {
        message.append("`")
                .append(subject.getDescription())
                .append("`");
    }

    private void putEndLine(StringBuilder message) {
        message.append("\n");
    }
}

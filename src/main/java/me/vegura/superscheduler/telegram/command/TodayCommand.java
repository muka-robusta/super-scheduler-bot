package me.vegura.superscheduler.telegram.command;

import me.vegura.superscheduler.converters.ScheduleDayTextConverter;
import me.vegura.superscheduler.domain.ScheduleEvent;
import me.vegura.superscheduler.service.ScheduleService;
import me.vegura.superscheduler.telegram.AbstractCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

@Component
public final class TodayCommand extends AbstractCommand {

    private static final String TODAY_BOT_COMMAND = "today";
    private static final String TODAY_BOT_COMMAND_DESCRIPTION = "Today's schedule";

    private final ScheduleService scheduleService;
    private final ScheduleDayTextConverter dayTextConverter;

    @Autowired
    public TodayCommand(ScheduleService scheduleService, ScheduleDayTextConverter dayTextConverter) {
        super(TODAY_BOT_COMMAND, TODAY_BOT_COMMAND_DESCRIPTION);
        this.scheduleService = scheduleService;
        this.dayTextConverter = dayTextConverter;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        final DayOfWeek dayOfWeekNow = LocalDate.now().getDayOfWeek();
        final Set<ScheduleEvent> scheduleByDay = scheduleService.findAllByDayOfWeek(dayOfWeekNow);
        final String scheduleMessageText = dayTextConverter.convert(scheduleByDay);

        SendMessage message = new SendMessage();
        message.setChatId(chat.getId().toString());
        message.setText(scheduleMessageText);
        execute(absSender, message, user);
    }
}

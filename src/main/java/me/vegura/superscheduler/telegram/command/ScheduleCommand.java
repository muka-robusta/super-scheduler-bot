package me.vegura.superscheduler.telegram.command;

import me.vegura.superscheduler.converters.ScheduleWeekTextConverter;
import me.vegura.superscheduler.domain.ScheduleEvent;
import me.vegura.superscheduler.service.ScheduleService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.Set;

@Component
public class ScheduleCommand extends AbstractCommand {

    private static final String SCHEDULE_BOT_COMMAND = "schedule";
    private static final String SCHEDULE_BOT_DESCRIPTION = "Get schedule for week";

    private final ScheduleService scheduleService;
    private final ScheduleWeekTextConverter scheduleConverter;

    public ScheduleCommand(ScheduleService scheduleService, ScheduleWeekTextConverter scheduleConverter) {
        super(SCHEDULE_BOT_COMMAND, SCHEDULE_BOT_DESCRIPTION);
        this.scheduleService = scheduleService;
        this.scheduleConverter = scheduleConverter;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        final Set<ScheduleEvent> scheduleForWeek = scheduleService.findAll();
        final String weekScheduleMessageText = scheduleConverter.convert(scheduleForWeek);

        final SendMessage message = new SendMessage();
        message.setChatId(chat.getId().toString());
        message.setText(weekScheduleMessageText);
        execute(absSender, message, user);
    }
}

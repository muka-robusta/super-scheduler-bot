package me.vegura.superscheduler.telegram.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class CalendarCommand extends AbstractCommand {

    private static final String CALENDAR_BOT_COMMAND = "calendar";
    private static final String CALENDAR_BOT_COMMAND_DESCRIPTION = "Shows today's date";

    @Autowired
    public CalendarCommand() {
        super(CALENDAR_BOT_COMMAND, CALENDAR_BOT_COMMAND_DESCRIPTION);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {

    }
}

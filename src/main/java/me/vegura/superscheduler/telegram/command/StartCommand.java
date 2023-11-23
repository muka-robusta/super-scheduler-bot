package me.vegura.superscheduler.telegram.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.List;

@Component
@Slf4j
public class StartCommand extends AbstractCommand {

    public static final String BOT_START_COMMAND = "start";
    public static final String BOT_START_COMMAND_DESCRIPTION = "Start bot";

    public StartCommand() {
        super(BOT_START_COMMAND, BOT_START_COMMAND_DESCRIPTION);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        log.info("Executing start command");
        SendMessage message = new SendMessage();
        message.setText("Welcome to CS bot!");
        message.setChatId(chat.getId());
        message.setReplyMarkup(mainMarkup());
        execute(absSender, message, user);
    }

    public ReplyKeyboardMarkup mainMarkup() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(List.of(
                new KeyboardRow(List.of(new KeyboardButton("Calendar"))),
                new KeyboardRow(List.of(new KeyboardButton("Reminder")))
        ));

        return replyKeyboardMarkup;
    }
}

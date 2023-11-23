package me.vegura.superscheduler.telegram.command;


import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public abstract class AbstractCommand extends BotCommand {

    public AbstractCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    protected void execute(AbsSender sender, SendMessage message, User user) {
        try {
            message.setParseMode(ParseMode.HTML);
            sender.execute(message);
        } catch (TelegramApiException ex) {
            log.error(ex.getMessage());
        }
    }

}

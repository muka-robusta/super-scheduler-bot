package me.vegura.superscheduler.telegram.command;

import me.vegura.superscheduler.service.NotificationService;
import me.vegura.superscheduler.telegram.QueueProcessorContext;
import me.vegura.superscheduler.telegram.processor.NotificationProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class RegisterNotificationCommand extends AbstractCommand {

    private static final String REGISTER_NOTIFICATION_BOT_COMMAND = "notification";
    private static final String REGISTER_NOTIFICATION_BOT_DESCRIPTION = "Create a notification";
    private static final String REQUEST_MESSAGE = "Send me event name and I will remind you in 1 minute";

    private final NotificationService notificationService;
    private final QueueProcessorContext context;

    @Autowired
    public RegisterNotificationCommand(NotificationService notificationService,
                                       QueueProcessorContext context) {
        super(REGISTER_NOTIFICATION_BOT_COMMAND, REGISTER_NOTIFICATION_BOT_DESCRIPTION);
        this.notificationService = notificationService;
        this.context = context;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        final String chatId = chat.getId().toString();
        context.requestProcessor(chatId, NotificationProcessor.class);

        final SendMessage message = new SendMessage();
        message.setChatId(chat.getId().toString());
        message.setText(REQUEST_MESSAGE);
        execute(absSender, message, user);
    }
}

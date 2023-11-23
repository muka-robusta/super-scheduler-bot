package me.vegura.superscheduler.telegram.processor;

import lombok.RequiredArgsConstructor;
import me.vegura.superscheduler.service.NotificationService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class NotificationProcessor extends AbstractProcessor {

    private final NotificationService notificationService;

    @Override
    public void process(Update update) {
        final Message message = update.getMessage();
        final String messageText = message.getText();
//        notificationService.remindIn1Minute(messageText, message.getChatId().toString());
    }
}

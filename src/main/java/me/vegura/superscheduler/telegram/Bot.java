package me.vegura.superscheduler.telegram;

import lombok.extern.slf4j.Slf4j;
import me.vegura.superscheduler.config.TelegramConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class Bot extends TelegramLongPollingCommandBot {

    private final TelegramConfig telegramConfig;
    private final QueueProcessorContext queueProcessorContext;

    public Bot(DefaultBotOptions botOptions,
               TelegramConfig config,
               CommandHolder commandHolder,
               QueueProcessorContext queueProcessorContext) {
        super(botOptions);
        this.telegramConfig = config;
        this.queueProcessorContext = queueProcessorContext;
        register(commandHolder.getTodayCommand());
        register(commandHolder.getRegisterNotificationCommand());
        register(commandHolder.getCalendarCommand());
        register(commandHolder.getStartCommand());
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        queueProcessorContext.checkForProcess(update);
    }

    @PostConstruct
    public void startBot() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            log.info("Starting bot context");
            telegramBotsApi.registerBot(this);
            log.info("Bot is ready to work");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return telegramConfig.getBOT_USERNAME();
    }

    @Override
    public String getBotToken() {
        return telegramConfig.getBOT_TOKEN();
    }
}


package me.vegura.superscheduler.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Configuration
@PropertySource("classpath:telegram.properties")
@Getter
public class TelegramConfig {

    @Value("${telegram.bot.token}")
    private String BOT_TOKEN;

    @Value("${telegram.bot.username}")
    private String BOT_USERNAME;

    @Bean
    public DefaultBotOptions botOptionsBean() {
        return new DefaultBotOptions();
    }

}

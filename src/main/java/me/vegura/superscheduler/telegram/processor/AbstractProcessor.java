package me.vegura.superscheduler.telegram.processor;

import lombok.RequiredArgsConstructor;
import me.vegura.superscheduler.telegram.Bot;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
public abstract class AbstractProcessor {

    public abstract void process(Update update);

}

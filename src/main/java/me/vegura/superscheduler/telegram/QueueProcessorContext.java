package me.vegura.superscheduler.telegram;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.vegura.superscheduler.telegram.processor.AbstractProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
@RequiredArgsConstructor
public class QueueProcessorContext {

    private Map<String, Class<? extends AbstractProcessor>> processingMap = new HashMap<>();
    private final ApplicationContext applicationContext;

    public void requestProcessor(String chatId, Class<? extends AbstractProcessor> processor) {
        processingMap.put(chatId, processor);
    }

    public void checkForProcess(Update update) {
        final String chatId = update.getMessage().getChatId().toString();
        if (!processingMap.containsKey(chatId))
            return;

        final Class<? extends AbstractProcessor> processorClass = processingMap.get(chatId);
        final AbstractProcessor processor = applicationContext.getBean(processorClass);
        processingMap.remove(chatId);
        processor.process(update);
    }

}

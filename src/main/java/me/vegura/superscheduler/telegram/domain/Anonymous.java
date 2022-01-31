package me.vegura.superscheduler.telegram.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

@Slf4j
@Data
public final class Anonymous {

    private static final String ERROR_MESSAGE_EXCEPTION = "User or chat cannot be null";

    private final User telegramUser;
    private final Chat telegramChat;
    private String displayedName;

    public Anonymous(User tUser, Chat tChat) {
        if (tUser == null || tChat == null) {
            log.error(ERROR_MESSAGE_EXCEPTION);
            throw new IllegalStateException(ERROR_MESSAGE_EXCEPTION);
        }

        telegramUser = tUser;
        telegramChat = tChat;
    }
}

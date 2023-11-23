package me.vegura.superscheduler.telegram;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.vegura.superscheduler.telegram.command.RegisterNotificationCommand;
import me.vegura.superscheduler.telegram.command.TodayCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter @RequiredArgsConstructor
public class CommandHolder {
    private final TodayCommand todayCommand;
    private final RegisterNotificationCommand registerNotificationCommand;
}

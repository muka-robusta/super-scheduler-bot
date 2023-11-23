package me.vegura.superscheduler.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data @Accessors(chain = true)
public class Notification {
    private Long eventId;
    private Instant notificationTime;
}

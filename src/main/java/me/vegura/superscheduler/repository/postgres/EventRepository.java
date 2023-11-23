package me.vegura.superscheduler.repository.postgres;

import me.vegura.superscheduler.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByTime(Instant time);
}

package me.vegura.superscheduler.domain;

import lombok.Data;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import java.time.Instant;

@Data
//@Entity
public class Event extends AbstractEntity {
    private String description;
    private Instant time;
}

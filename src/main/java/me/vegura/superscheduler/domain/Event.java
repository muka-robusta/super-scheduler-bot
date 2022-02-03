package me.vegura.superscheduler.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper = true)
//@Entity
public class Event extends AbstractEntity {
    private String name;
    private String description;
    private Instant time;
}

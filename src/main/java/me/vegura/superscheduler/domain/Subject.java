package me.vegura.superscheduler.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;

@Data @Accessors(chain = true) @EqualsAndHashCode(callSuper = true)
@Entity
public class Subject extends AbstractEntity {
    private String name;
    private String description;
    private String tutorName;
}

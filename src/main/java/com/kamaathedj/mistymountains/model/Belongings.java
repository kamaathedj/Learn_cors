package com.kamaathedj.mistymountains.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter

@Table(value = "prisonerStuff")
public class Belongings {
    @Id
    long tagId;
    String name;
    Long prisonerId;

    public Belongings(long tagId, String name, Long prisonerId) {
        this.tagId = tagId;
        this.name = name;
        this.prisonerId = prisonerId;
    }
}

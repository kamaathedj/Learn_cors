package com.kamaathedj.mistymountains.model;

import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serial;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
@Table(value = "prisoner")
public class Prisoner {

    @Id
    long id;
    String name;
    String duties;
    List<Belongings> prisonerStuff;
    @Column(value = "prisonedwhen")
    @CreatedDate
    LocalDate prisonedWhen;

    public Prisoner(String name, String duties, LocalDate prisonedWhen) {
        this.name = name;
        this.duties = duties;
        this.prisonedWhen = prisonedWhen;
    }

};



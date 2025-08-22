package com.brieuc.dailymon.entity.entry;

import com.brieuc.dailymon.entity.Benefit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entry_sport")
public class EntrySport extends Entry {

    @Column(name = "kcal")
    Integer kcal;

    @Column(name = "duration")  // in minutes
    Integer duration;

    @Column(name = "aerobic")
    Double aerobic;

    @Column(name = "anaerobic")
    Double anaerobic;

    @Enumerated(EnumType.STRING)
    @Column(name = "benefit")  // Tempo, Sprint, Base, Anaerobic,..
    Benefit benefit;
}

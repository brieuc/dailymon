package com.brieuc.dailymon.entity.entry;


import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class Entry {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    UUID id;

    @Version
    long version;

    @Column(name = "date")
    LocalDate date;

    @Column(name = "title")
    String title;
    
    @Column(name = "description")
    String description;
}
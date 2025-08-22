package com.brieuc.dailymon.entity.entry;


import java.time.LocalDate;
import java.util.UUID;

import com.brieuc.dailymon.entity.model.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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

    @ManyToOne
    @JoinColumn(name = "modelid", referencedColumnName = "id")
    Model model;
}
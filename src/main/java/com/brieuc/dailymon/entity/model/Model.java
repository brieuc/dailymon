package com.brieuc.dailymon.entity.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Model {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    UUID id;

    @Version
    long version;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;
}

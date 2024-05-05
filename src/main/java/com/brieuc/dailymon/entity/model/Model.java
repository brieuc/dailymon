package com.brieuc.dailymon.entity.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Model {

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

package com.brieuc.dailymon.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "model")
public class Model {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    UUID id;

    @Column(name = "label")
    String label;

    @Column(name = "description")
    String description;

    @Column(name = "kcal")
    Integer kcal;

    @Column(name = "image")
    String image;
}

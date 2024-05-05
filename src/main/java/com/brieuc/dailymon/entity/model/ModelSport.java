package com.brieuc.dailymon.entity.model;

import com.brieuc.dailymon.entity.Sport;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "model_sport")
public class ModelSport extends Model {

    @Enumerated(EnumType.STRING)
    @Column(name = "sport") // Running, HIIT, Boxe, Skate
    Sport sport;

    @Column(name = "image")
    String image;
}

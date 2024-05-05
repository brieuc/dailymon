package com.brieuc.dailymon.entity.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor

@Entity
@Table(name = "model_food")
public class ModelFood extends Model {

    @Column(name = "kcal")
    Integer kcal;

    @Column(name = "image")
    String image;
}

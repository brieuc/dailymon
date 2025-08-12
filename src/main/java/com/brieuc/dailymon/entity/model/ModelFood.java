package com.brieuc.dailymon.entity.model;



import com.brieuc.dailymon.entity.FoodType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "model_food")
public class ModelFood extends Model {

    @Column(name = "kcal")
    Integer kcal;

    @Column(name = "food_type")
    FoodType foodType;

    @Column(name = "image")
    String image;
}

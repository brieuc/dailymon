package com.brieuc.dailymon.dto;

import com.brieuc.dailymon.entity.FoodType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ModelFoodDto extends ModelDto {
    
    FoodType foodType;
    Integer kcal;
}

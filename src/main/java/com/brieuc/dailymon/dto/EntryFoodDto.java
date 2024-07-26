package com.brieuc.dailymon.dto;

import com.brieuc.dailymon.entity.FoodType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntryFoodDto extends EntryDto {

    Double quantity;
    FoodType foodType;
    ModelFoodDto model;
    final String type="FOOD";
}

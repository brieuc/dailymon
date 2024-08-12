package com.brieuc.dailymon.dto;

import com.brieuc.dailymon.entity.FoodType;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Quantity couldn't be null")
    @Digits(integer = 3, fraction = 0, message = "Incorrect quantity input")
    Double quantity;
    FoodType foodType;
    ModelFoodDto model;
    final String type="FOOD";
}

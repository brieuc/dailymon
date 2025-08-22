package com.brieuc.dailymon.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
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
public class EntryFoodDto extends EntryDto {

    @NotNull(message = "Quantity couldn't be null")
    @Digits(integer = 3, fraction = 0, message = "Incorrect quantity input")
    Double quantity;
    ModelFoodDto model;
    final String type="FOOD";
}

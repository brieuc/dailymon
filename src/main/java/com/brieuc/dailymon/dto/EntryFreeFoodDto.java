package com.brieuc.dailymon.dto;

import com.brieuc.dailymon.entity.FoodType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EntryFreeFoodDto extends EntryFreeDto {
      FoodType foodType;
      Integer kcal;
}

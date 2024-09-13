package com.brieuc.dailymon.dto;

import com.brieuc.dailymon.entity.FoodType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntryFreeFoodDto extends EntryFreeDto {
      FoodType foodType;
      Integer kcal;
}

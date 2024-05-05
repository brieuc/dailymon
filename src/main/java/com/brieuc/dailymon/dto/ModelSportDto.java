package com.brieuc.dailymon.dto;

import com.brieuc.dailymon.entity.Sport;

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
public class ModelSportDto extends ModelDto {
    
    Sport sport;
}

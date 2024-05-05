package com.brieuc.dailymon.dto;

import com.brieuc.dailymon.entity.Benefit;

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
public class EntrySportDto extends EntryDto {
    
    Integer kcal;
    Double aerobic;
    Double anaerobic;
    Integer duration;
    Benefit benefit;
    final String type="SPORT";
}

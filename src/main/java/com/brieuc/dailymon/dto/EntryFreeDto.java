package com.brieuc.dailymon.dto;

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
public class EntryFreeDto extends EntryDto {
      ModelFreeDto model;
      final String type="FREE";
}

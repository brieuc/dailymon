package com.brieuc.dailymon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class EntryFreeDto extends EntryDto {
      final String type="FREE";
}

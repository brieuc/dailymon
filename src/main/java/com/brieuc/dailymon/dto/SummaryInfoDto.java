package com.brieuc.dailymon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class SummaryInfoDto {
      
      int spentKcal;
      int ingestedKcal;
      int sportDuration;
      int drinkingBeer;

      public SummaryInfoDto(int spentKcal, int ingestedKcal, int sportDuration,
                              int drinkingBeer) {
            this.spentKcal = spentKcal;
            this.ingestedKcal = ingestedKcal;
            this.sportDuration = sportDuration;
            this.drinkingBeer = drinkingBeer;
      }
}

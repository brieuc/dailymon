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
      
      Double spentKcal;
      Double ingestedKcal;
      Double sportDuration;
      Double drinkingBeer;
      Double aerobic;
      Double anaerobic;

      public SummaryInfoDto(Double spentKcal, Double ingestedKcal, Double sportDuration, Double drinkingBeer,
            Double aerobic, Double anaerobic) {
            this.spentKcal = spentKcal;
            this.ingestedKcal = ingestedKcal;
            this.sportDuration = sportDuration;
            this.drinkingBeer = drinkingBeer;
            this.aerobic = aerobic;
            this.anaerobic = anaerobic;
      }
}

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
      
      double spentKcal;
      double ingestedKcal;
      double sportDuration;
      double drinkingBeer;
      double aerobic;
      double anaerobic;

      public SummaryInfoDto(double spentKcal, double ingestedKcal, double sportDuration, double drinkingBeer,
            double aerobic, double anaerobic) {
            this.spentKcal = spentKcal;
            this.ingestedKcal = ingestedKcal;
            this.sportDuration = sportDuration;
            this.drinkingBeer = drinkingBeer;
            this.aerobic = aerobic;
            this.anaerobic = anaerobic;
      }
}

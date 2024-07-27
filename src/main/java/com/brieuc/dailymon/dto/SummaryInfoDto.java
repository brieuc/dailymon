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
      
      float spentKcal;
      float ingestedKcal;
      float sportDuration;
      float drinkingBeer;
      float aerobic;
      float anaerobic;

      public SummaryInfoDto(float spentKcal, float ingestedKcal, float sportDuration, float drinkingBeer,
                  float aerobic, float anaerobic) {
            this.spentKcal = spentKcal;
            this.ingestedKcal = ingestedKcal;
            this.sportDuration = sportDuration;
            this.drinkingBeer = drinkingBeer;
            this.aerobic = aerobic;
            this.anaerobic = anaerobic;
      }
}

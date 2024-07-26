package com.brieuc.dailymon;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CommonUtilTest {

      @Test
      void ListOfDates_ShouldReturnTheFirstMondayDate() {
            // The 5th of june is wednesday. So the first monday is 3rd,
            // the first date should be 2024-06-03
            LocalDate minDate = LocalDate.of(2024, 6, 5);
            int nbOfDays = 7;
            List<String> dates = CommonUtil.getListOfDates(minDate, nbOfDays);
            // The list is sorted desc so we check 
            assertEquals("2024-06-03", dates.get(dates.size()-1));
            // We are the monday 2024-06-17 so we should a list
            // 2024-06-17, 2024-06-10, 2024-06-03
            assertEquals("2024-06-10", dates.get(dates.size()-2));
      }

}

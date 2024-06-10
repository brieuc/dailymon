package com.brieuc.dailymon;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CommonUtil {

      public static List<String> getListOfDates(LocalDate minDate, Integer nbOfDays) {
        List<String> listOfDates = new ArrayList<>();
        DayOfWeek dayOfWeek = minDate.getDayOfWeek();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        int iDay = dayOfWeek.getValue();
        // LUNDI, MARDI, MERCERDI, JEUDI, VENDREDI, SAMEDI, DIMANCHE
        //   1      2        3       4        5       6        7
        // Si Dimanche (7), pour trouver le lundi, in fait Date - 6 (iDay - 1)
        // Si Mercredi (3), pour trouver le lundi, on fait Date - 2 (iDay - 1)
        // Si Lundi (1), pour trouver le lundi, on fait Date - 0 (iDay - 1)
        int i = 1;
        int dayToSubstract = iDay - 1;
        LocalDate firstDay = minDate.minusDays(dayToSubstract);
        LocalDate currentDay = firstDay;
        while (currentDay.isBefore(LocalDate.now()) ) {
            listOfDates.add(currentDay.format(formatter));
            currentDay = firstDay.plusDays(i * nbOfDays);
            i++;
        }
        listOfDates.sort(Comparator.reverseOrder());
        return listOfDates;
      }
      
}

package com.brieuc.dailymon.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.brieuc.dailymon.entity.FoodType;
import com.brieuc.dailymon.entity.entry.Entry;
import com.brieuc.dailymon.entity.entry.EntryFood;
import com.brieuc.dailymon.entity.entry.EntryFreeFood;
import com.brieuc.dailymon.entity.entry.EntrySport;
import com.brieuc.dailymon.entity.model.ModelFood;
import com.brieuc.dailymon.repository.EntryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EntryServiceImpl implements EntryService {

      private final EntryRepository entryRepository;

      @Override
      public LocalDate getMinEntryDate() {
            return entryRepository.findMinEntryDate();
      }

      @Override
      public List<Entry> getEntriesByDate(LocalDate date) {
            return entryRepository.findByDate(date);
      }

      @Override
      public HashMap<String, Double> getSummaryInfo(LocalDate fromDate, LocalDate toDate) {
   
        int sportDuration = 0;
        double anaerobic = 0.0;
        double aerobic = 0.0;
        int spentKcal = 0;
        int ingestedKcal = 0;
        double drinkingBeer = 0;
        int i = 0;
        LocalDate currentDate = fromDate;

        while (!currentDate.isAfter(toDate)) {
            List<Entry> entries = entryRepository.findByDate(currentDate);
            for (Entry entry:entries) {
                  if (entry instanceof EntrySport entrySport) {
                        sportDuration = sportDuration + entrySport.getDuration();
                        spentKcal = spentKcal + entrySport.getKcal();
                        anaerobic = anaerobic + entrySport.getAnaerobic();
                        aerobic = aerobic + entrySport.getAerobic();  
                  }
                  if (entry instanceof EntryFood entryFood) {
                        ingestedKcal = ingestedKcal + (entryFood.getQuantity().intValue() * ((ModelFood) entryFood.getModel()).getKcal());
                        drinkingBeer = drinkingBeer + Optional.of(entryFood)
                                    .filter(e -> ((ModelFood) e.getModel()).getFoodType().equals(FoodType.ALCOHOL))
                                    .map(e -> e.getQuantity() * ((ModelFood) entryFood.getModel()).getKcal())
                                    .orElse(0.0);
                  }
                  if (entry instanceof EntryFreeFood entryFreeFood) {
                        ingestedKcal = ingestedKcal + entryFreeFood.getKcal();
                                          // Add the alcohol from free food
                        drinkingBeer = drinkingBeer + Optional.of(entryFreeFood)
                                    .filter(e -> e.getFoodType().equals(FoodType.ALCOHOL))
                                    .map(e -> e.getKcal() * 1.0)
                                    .orElse(0.0);
                  }
            }
            i++;
            currentDate = fromDate.plusDays(i);    
            }

            HashMap<String, Double> map = new HashMap<>();
            map.put("spentKcal", Double.valueOf(spentKcal));
            map.put("ingestedKcal", Double.valueOf(ingestedKcal));
            map.put("sportDuration", Double.valueOf(sportDuration));
            map.put("anaerobic", anaerobic);
            map.put("aerobic", aerobic);
            map.put("drinkingBeer", drinkingBeer);
            return map;
      }

      @Override
      public Entry createEntry(Entry entry) {
            return entryRepository.save(entry);
      }

      @Override
      public Entry updateEntry(Entry newEntry) {
            if (!entryRepository.existsById(newEntry.getId())) {
                  throw new RuntimeException("Entry to update not found");
            }
            return entryRepository.save(newEntry);
      }
      

      @Override
      public void deleteEntry(UUID id) {
            if (!entryRepository.existsById(id)) {
                  throw new RuntimeException("Entry to delete not found");
            }
            entryRepository.deleteById(id);
      }
      
}

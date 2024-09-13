package com.brieuc.dailymon.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.brieuc.dailymon.entity.FoodType;
import com.brieuc.dailymon.entity.entry.EntryFreeFood;
import com.brieuc.dailymon.entity.model.ModelFree;
import com.brieuc.dailymon.repository.EntryFreeFoodRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EntryFreeFoodService {
      private final EntryFreeFoodRepository entryRepository;


      public EntryFreeFood createEntry(ModelFree model, LocalDate date, String title, String desc,
                              FoodType foodType, Integer kcal) {

            //EntryFreeFood entry = (EntryFreeFood) EntryFreeFood.builder().model(model).build();
            EntryFreeFood entry = new EntryFreeFood();
            entry.setModel(model);
            entry.setId(UUID.randomUUID());
            entry.setDate(date);
            entry.setTitle(title);
            entry.setDescription(desc);
            entry.setFoodType(foodType);
            entry.setKcal(kcal);
            entryRepository.save(entry);
            return entry;
      }
    
      public Optional<EntryFreeFood> getEntryById(UUID id) {
            return entryRepository.findById(id);
      }

      public List<EntryFreeFood> getEntries() {
            return entryRepository.findAll();
      }
      
      public List<EntryFreeFood> getEntriesByDate(LocalDate date) {
            return entryRepository.findByDate(date);
      }

      public EntryFreeFood updateEntry(EntryFreeFood entry, String title, String description) {
            entry.setTitle(title);
            entry.setDescription(description);
            entryRepository.save(entry);
            return entry;
      }

      public void deleteEntry(EntryFreeFood entryFree) {
            entryRepository.delete(entryFree);
      }
}
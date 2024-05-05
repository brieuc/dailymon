package com.brieuc.dailymon.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brieuc.dailymon.entity.entry.EntryFood;
import com.brieuc.dailymon.entity.model.ModelFood;
import com.brieuc.dailymon.repository.EntryFoodRepository;

@Service
public class EntryFoodService {
    private EntryFoodRepository entryRepository;

    @Autowired
    public EntryFoodService(EntryFoodRepository entryRepository, ModelFoodService modelService) {
        this.entryRepository = entryRepository;
    }

    public EntryFood createEntry(ModelFood model, LocalDate date, String title, String desc,
                                Double quantity) {

        EntryFood entry = EntryFood.builder().model(model).quantity(quantity).build();
        entry.setId(UUID.randomUUID());
        entry.setDate(date);
        entry.setTitle(title);
        entry.setDescription(desc);
        entryRepository.save(entry);
        return entry;
    }
    
    public LocalDate getMinEntryDate() {

        LocalDate minDate = entryRepository.findMinEntryDate();
        if (minDate != null) {
            return minDate;
        }
        return LocalDate.now();
    }

    public Optional<EntryFood> getEntryById(UUID id) {
        return this.entryRepository.findById(id);
    }

    public List<EntryFood> getEntries() {
        return this.entryRepository.findAll();
    }
    
    public List<EntryFood> getEntriesByDate(LocalDate date) {
        return this.entryRepository.findByDate(date);
    }

    public EntryFood updateEntry(EntryFood entry, Double quantity) {
        entry.setQuantity(quantity);
        this.entryRepository.save(entry);
        return entry;
    }
}

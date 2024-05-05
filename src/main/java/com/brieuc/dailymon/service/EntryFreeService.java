package com.brieuc.dailymon.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brieuc.dailymon.entity.entry.EntryFree;
import com.brieuc.dailymon.entity.model.ModelFree;
import com.brieuc.dailymon.repository.EntryFreeRepository;


@Service
public class EntryFreeService {

    private EntryFreeRepository entryRepository;

    @Autowired
    public EntryFreeService(EntryFreeRepository entryRepository, ModelFreeService modelService) {
        this.entryRepository = entryRepository;
    }

    public EntryFree createEntry(ModelFree model, LocalDate date, String title, String desc) {

        EntryFree entry = EntryFree.builder().model(model).build();
        entry.setId(UUID.randomUUID());
        entry.setDate(date);
        entry.setTitle(title);
        entry.setDescription(desc);
        entryRepository.save(entry);
        return entry;
    }
    
    public Optional<EntryFree> getEntryById(UUID id) {
        return this.entryRepository.findById(id);
    }

    public List<EntryFree> getEntries() {
        return this.entryRepository.findAll();
    }
    
    public List<EntryFree> getEntriesByDate(LocalDate date) {
        return this.entryRepository.findByDate(date);
    }

    public EntryFree updateEntry(EntryFree entry, String description) {
        entry.setDescription(description);
        this.entryRepository.save(entry);
        return entry;
    }
}

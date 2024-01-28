package com.brieuc.dailymon.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brieuc.dailymon.entity.Entry;
import com.brieuc.dailymon.entity.Model;
import com.brieuc.dailymon.repository.EntryRepository;


@Service
public class EntryService {

    private EntryRepository entryRepository;

    @Autowired
    public EntryService(EntryRepository entryRepository, ModelService modelService) {
        this.entryRepository = entryRepository;
    }

    public Entry createEntry(   Model model, LocalDate date, String desc,
                                Double quantity) {

        Entry entry = Entry.builder().model(model).date(date).description(desc).build();
        entry.setId(UUID.randomUUID());
        this.entryRepository.save(entry);
        return entry;
    }
    
    public Optional<Entry> getEntryById(UUID id) {
        return this.entryRepository.findById(id);
    }

    public List<Entry> getEntries() {
        return this.entryRepository.findAll();
    }
    
    public List<Entry> getEntriesByDate(LocalDate date) {
        return this.entryRepository.findByDate(date);
    }

    public Entry updateEntry(Entry entry, String description, Double quantity) {
        System.out.println(quantity);
        entry.setQuantity(quantity);
        entry.setDescription(description);
        this.entryRepository.save(entry);
        return entry;
    }
}

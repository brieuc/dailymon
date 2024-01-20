package com.brieuc.dailymon.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.brieuc.dailymon.entity.Entry;
import com.brieuc.dailymon.repository.EntryRepository;


@Service
public class EntryService {

        private EntryRepository entryRepository;

    @Autowired
    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }
    
    public List<Entry> getEntries() {
        return this.entryRepository.findAll();
    }
    
    public List<Entry> getEntriesByDate(LocalDate date) {
        return this.entryRepository.findByDate(date);
    }
}

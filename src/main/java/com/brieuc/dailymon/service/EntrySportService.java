package com.brieuc.dailymon.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brieuc.dailymon.entity.Benefit;
import com.brieuc.dailymon.entity.entry.EntrySport;
import com.brieuc.dailymon.entity.model.ModelSport;
import com.brieuc.dailymon.repository.EntrySportRepository;

@Service
public class EntrySportService {

    private EntrySportRepository entryRepository;

    @Autowired
    public EntrySportService(EntrySportRepository entryRepository, ModelFreeService modelService) {
        this.entryRepository = entryRepository;
    }

    public EntrySport createEntry(ModelSport model, LocalDate date, String title, String desc,
                                    Benefit benefit, Integer duration, Integer kcal,
                                    Double aerobic, Double anaerobic) {

        EntrySport entry = EntrySport.builder()
                            .model(model)
                            .duration(duration)
                            .kcal(kcal)
                            .benefit(benefit)
                            .aerobic(aerobic)
                            .anaerobic(aerobic).build();
        entry.setId(UUID.randomUUID());
        entry.setDate(date);
        entry.setDescription(desc);
        entry.setTitle(title);
        entry.setModel(model);
        entryRepository.save(entry);
        return entry;
    }
    
    public Optional<EntrySport> getEntryById(UUID id) {
        return this.entryRepository.findById(id);
    }

    public List<EntrySport> getEntries() {
        return this.entryRepository.findAll();
    }
    
    public List<EntrySport> getEntriesByDate(LocalDate date) {
        return this.entryRepository.findByDate(date);
    }

    public EntrySport updateEntry(EntrySport entry, String title, String description, Benefit benefit,
                                    Integer duration, Integer kcal, Double aerobic, Double anaerobic) {
        
        entry.setTitle(title);
        entry.setDescription(description);
        entry.setDuration(duration);
        entry.setKcal(kcal);
        entry.setBenefit(benefit);
        entry.setAerobic(aerobic);
        entry.setAnaerobic(anaerobic);
        this.entryRepository.save(entry);
        return entry;
    }
}

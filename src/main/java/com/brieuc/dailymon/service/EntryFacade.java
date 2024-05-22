package com.brieuc.dailymon.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brieuc.dailymon.dto.EntryDto;
import com.brieuc.dailymon.dto.EntryFoodDto;
import com.brieuc.dailymon.dto.EntryFreeDto;
import com.brieuc.dailymon.dto.EntrySportDto;
import com.brieuc.dailymon.entity.entry.Entry;
import com.brieuc.dailymon.entity.entry.EntryFood;
import com.brieuc.dailymon.entity.entry.EntryFree;
import com.brieuc.dailymon.entity.entry.EntrySport;
import com.brieuc.dailymon.entity.model.ModelFood;
import com.brieuc.dailymon.entity.model.ModelFree;
import com.brieuc.dailymon.entity.model.ModelSport;

@Component
public class EntryFacade {
    
    private EntryFoodService entryFoodService;
    private EntryFreeService entryFreeService;
    private EntrySportService entrySportService;

    private ModelFoodService modelFoodService;
    private ModelFreeService modelFreeService;
    private ModelSportService modelSportService;

    @Autowired
    public EntryFacade(EntryFoodService entryFoodService, EntryFreeService entryFreeService,
                        EntrySportService entrySportService, ModelFoodService modelFoodService,
                        ModelFreeService modelFreeService, ModelSportService modelSportService) {

        this.entryFoodService = entryFoodService;
        this.entrySportService = entrySportService;
        this.entryFreeService = entryFreeService;
        this.modelFoodService = modelFoodService;
        this.modelSportService = modelSportService;
        this.modelFreeService = modelFreeService;
    }

    public LocalDate getMinEntryDate() {
        return entryFoodService.getMinEntryDate();
    }

    public List<? extends Entry> getEntriesByDate(LocalDate date) {
        List<EntryFood> entriesFood = entryFoodService.getEntriesByDate(date);
        List<EntryFree> entriesFree = entryFreeService.getEntriesByDate(date);
        List<EntrySport> entriesSport = entrySportService.getEntriesByDate(date);
        return Stream.concat(entriesSport.stream(), Stream.concat(entriesFood.stream(), entriesFree.stream())).toList();
    }

    public List<EntryFood> getFoodEntriesByDate(LocalDate date) {
        return entryFoodService.getEntriesByDate(date).stream().toList();
    }

    public Entry createEntry(EntryDto entryDto) {

        // The e is from EntryFoodDto in that case, that's why we're using e and not entryDto which
        // doesn't have the specific getter from EntryFoodDto.
        if (entryDto instanceof EntryFoodDto entryFoodDto) {
            UUID modelId = entryFoodDto.getModelId();
            Optional<ModelFood> model = this.modelFoodService.getModelById(modelId);
            if (model.isPresent()) {
                EntryFood entryFood = this.entryFoodService.createEntry(model.get(), entryFoodDto.getDate(), entryDto.getTitle(), entryFoodDto.getDescription(), entryFoodDto.getQuantity());
                return entryFood;
            }
            else {
                throw new RuntimeException("model " + entryFoodDto.getModelId() + " not found");
            }
        }
        if (entryDto instanceof EntrySportDto entrySportDto) {
            UUID modelId = entrySportDto.getModelId();
            Optional<ModelSport> model = this.modelSportService.getModelById(modelId);
            if (model.isPresent()) {
                EntrySport entrySport = this.entrySportService.createEntry(model.get(), entrySportDto.getDate(), entrySportDto.getTitle(),
                                                            entrySportDto.getDescription(),
                                                            entrySportDto.getBenefit(), entrySportDto.getDuration(), entrySportDto.getKcal(), entrySportDto.getAerobic(),
                                                            entrySportDto.getAnaerobic());
                return entrySport;
            }
            else {
                throw new RuntimeException("model " + entrySportDto.getModelId() + " not found");
            }  
        }
        if (entryDto instanceof EntryFreeDto entryFreeDto) {
            UUID modelId = entryFreeDto.getModelId();
            Optional<ModelFree> model = this.modelFreeService.getModelById(modelId);
            if (model.isPresent()) {
                EntryFree entryFree = this.entryFreeService.createEntry(model.get(), entryFreeDto.getDate(),
                                                        entryFreeDto.getTitle(),                                                        
                                                        entryFreeDto.getDescription());
                return entryFree;
            }
            else {
                throw new RuntimeException("model " + entryFreeDto.getModelId() + " not found");
            }  
        }
        return null;
    }

    public Entry updateEntry(EntryDto entryDto) {
        if (entryDto instanceof EntryFoodDto entryFoodDto) {
            Optional<EntryFood> entryFood = this.entryFoodService.getEntryById(entryFoodDto.getId());
            if (entryFood.isPresent()) {
                EntryFood entryFoodUpdate = entryFoodService.updateEntry(entryFood.get(), entryFoodDto.getQuantity());
                return entryFoodUpdate;
            }
        }
        if (entryDto instanceof EntrySportDto entrySportDto) {
            Optional<EntrySport> entrySport = this.entrySportService.getEntryById(entrySportDto.getId());
            if (entrySport.isPresent()) {
                EntrySport entrySportUpdate = entrySportService.updateEntry(entrySport.get(), entrySportDto.getTitle(), entrySportDto.getDescription(), entrySportDto.getBenefit(), entrySportDto.getDuration(), entrySportDto.getKcal(), entrySportDto.getAerobic(), entrySportDto.getAnaerobic());
                return entrySportUpdate;
            }
        }
        if (entryDto instanceof EntryFreeDto entryFreeDto) {
            Optional<EntryFree> entryFree = this.entryFreeService.getEntryById(entryFreeDto.getId());
            if (entryFree.isPresent()) {
                EntryFree entryFreeUpdate = entryFreeService.updateEntry(entryFree.get(), entryFreeDto.getTitle(), entryFreeDto.getDescription());
                return entryFreeUpdate;
            }
        }
        return null;
    }

    public void deleteEntry(UUID id) {
        Optional<EntryFood> entryFood = this.entryFoodService.getEntryById(id);
        if (entryFood.isPresent()) {
            entryFoodService.deleteEntry(entryFood.get());
        }

        Optional<EntrySport> entrySport = this.entrySportService.getEntryById(id);
        if (entrySport.isPresent()) {
            entrySportService.deleteEntry(entrySport.get());
        }

        Optional<EntryFree> entryFree = this.entryFreeService.getEntryById(id);
        if (entryFree.isPresent()) {
            entryFreeService.deleteEntry(entryFree.get());
        }
    }
}

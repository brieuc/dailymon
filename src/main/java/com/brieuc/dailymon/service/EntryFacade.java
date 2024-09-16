package com.brieuc.dailymon.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.brieuc.dailymon.dto.EntryDto;
import com.brieuc.dailymon.dto.EntryFoodDto;
import com.brieuc.dailymon.dto.EntryFreeDto;
import com.brieuc.dailymon.dto.EntryFreeFoodDto;
import com.brieuc.dailymon.dto.EntrySportDto;
import com.brieuc.dailymon.entity.FoodType;
import com.brieuc.dailymon.entity.entry.Entry;
import com.brieuc.dailymon.entity.entry.EntryFood;
import com.brieuc.dailymon.entity.entry.EntryFree;
import com.brieuc.dailymon.entity.entry.EntryFreeFood;
import com.brieuc.dailymon.entity.entry.EntrySport;
import com.brieuc.dailymon.entity.model.ModelFood;
import com.brieuc.dailymon.entity.model.ModelFree;
import com.brieuc.dailymon.entity.model.ModelSport;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class EntryFacade {
    
    private final EntryFoodService entryFoodService;
    private final EntryFreeService entryFreeService;
    private final EntrySportService entrySportService;
    private final EntryFreeFoodService entryFreeFoodService;

    private final ModelFoodService modelFoodService;
    private final ModelFreeService modelFreeService;
    private final ModelSportService modelSportService;

    public LocalDate getMinEntryDate() {
        return entryFoodService.getMinEntryDate();
    }

    public List<? extends Entry> getEntriesByDate(LocalDate date) {
        List<EntryFood> entriesFood = entryFoodService.getEntriesByDate(date);
 
        List<EntrySport> entriesSport = entrySportService.getEntriesByDate(date);
        List<EntryFree> entriesFree = entryFreeService.getEntriesByDate(date);
        List<EntryFreeFood> entriesFreeFood = entryFreeFoodService.getEntriesByDate(date);
        return Stream.concat(Stream.concat(entriesSport.stream(), entriesFood.stream()), 
                    Stream.concat(entriesFree.stream(), entriesFreeFood.stream())).toList();
    }

    public List<EntryFood> getFoodEntriesByDate(LocalDate date) {
        return entryFoodService.getEntriesByDate(date).stream().toList();
    }

    public HashMap<String, Double> getSummaryInfo(LocalDate fromDate, LocalDate toDate) {
        
        int sportDuration = 0;
        double anaerobic = 0.0;
        double aerobic = 0.0;
        int spentKcal = 0;
        int ingestedKcal = 0;
        double drinkingBeer = 0.0;
        int i = 1;
        LocalDate currentDate = fromDate;
        while (!currentDate.isAfter(toDate)) {

            List<EntrySport> entriesSport = entrySportService.getEntriesByDate(currentDate);
            for (EntrySport entrySport:entriesSport) {
                sportDuration = sportDuration + entrySport.getDuration();
                spentKcal = spentKcal + entrySport.getKcal();
                anaerobic = anaerobic + entrySport.getAnaerobic();
                aerobic = aerobic + entrySport.getAerobic();
            }
            List<EntryFood> entriesFood = entryFoodService.getEntriesByDate(currentDate);
            for (EntryFood entryFood:entriesFood) {
                ingestedKcal = ingestedKcal + (entryFood.getQuantity().intValue() * entryFood.getModel().getKcal());
            }
            // Add the global entry free food kcal
            ingestedKcal = ingestedKcal + entryFreeFoodService.getEntriesByDate(currentDate).stream().mapToInt(e -> e.getKcal()).sum();

            drinkingBeer = drinkingBeer + entriesFood.stream()
                                    .filter(e -> e.getModel().getFoodType() != null)
                                    .filter(e -> e.getModel().getFoodType().equals(FoodType.ALCOHOL))
                                    .mapToDouble(e -> (e.getQuantity() * e.getModel().getKcal()))
                                    .sum();

            // Add the alcohol from free food
            drinkingBeer = drinkingBeer + entryFreeFoodService.getEntriesByDate(currentDate)
                    .stream().filter(e -> e.getFoodType() == FoodType.ALCOHOL).mapToInt(e -> e.getKcal()).sum();
            
                        

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

    public EntryFreeFood createEntry(EntryFreeFoodDto entryFreeFoodDto) {
        UUID modelId = entryFreeFoodDto.getModelId();
        Optional<ModelFree> model = modelFreeService.getModelById(modelId);
        if (model.isPresent()) {
            EntryFreeFood entryFreeFood = entryFreeFoodService.createEntry(model.get(), entryFreeFoodDto.getDate(),
                                entryFreeFoodDto.getTitle(),                                                        
                                entryFreeFoodDto.getDescription(),
                                entryFreeFoodDto.getFoodType(),
                                entryFreeFoodDto.getKcal());
            return entryFreeFood;
        }
        else {
            throw new RuntimeException("model " + entryFreeFoodDto.getModelId() + " not found");
        }  
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
                EntryFood entryFoodUpdate = entryFoodService.updateEntry(entryFood.get(),
                    entryFoodDto.getTitle(), entryFoodDto.getDescription(), entryFoodDto.getQuantity());
                return entryFoodUpdate;
            }
        }
        if (entryDto instanceof EntrySportDto entrySportDto) {
            Optional<EntrySport> entrySport = this.entrySportService.getEntryById(entrySportDto.getId());
            if (entrySport.isPresent()) {
                EntrySport entrySportUpdate = entrySportService.updateEntry(entrySport.get(),
                    entrySportDto.getTitle(), entrySportDto.getDescription(), entrySportDto.getBenefit(), entrySportDto.getDuration(), entrySportDto.getKcal(), entrySportDto.getAerobic(), entrySportDto.getAnaerobic());
                return entrySportUpdate;
            }
        }
        if (entryDto instanceof EntryFreeDto entryFreeDto) {
            Optional<EntryFree> entryFree = this.entryFreeService.getEntryById(entryFreeDto.getId());
            if (entryFree.isPresent()) {
                EntryFree entryFreeUpdate = entryFreeService.updateEntry(entryFree.get(),
                    entryFreeDto.getTitle(), entryFreeDto.getDescription());
                return entryFreeUpdate;
            }
        }
        if (entryDto instanceof EntryFreeFoodDto entryFreeFoodDto) {
            Optional<EntryFreeFood> entryFreeFood = this.entryFreeFoodService.getEntryById(entryFreeFoodDto.getId());
            if (entryFreeFood.isPresent()) {
                EntryFreeFood entryFreeUpdate = entryFreeFoodService.updateEntry(entryFreeFood.get(),
                                entryFreeFoodDto.getTitle(), entryFreeFoodDto.getDescription(),
                                entryFreeFoodDto.getFoodType(), entryFreeFoodDto.getKcal());
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
        Optional<EntryFreeFood> entryFreeFood = this.entryFreeFoodService.getEntryById(id);
        if (entryFreeFood.isPresent()) {
            entryFreeFoodService.deleteEntry(entryFreeFood.get());
        }
    }
}

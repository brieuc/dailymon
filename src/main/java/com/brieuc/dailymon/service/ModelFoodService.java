package com.brieuc.dailymon.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.brieuc.dailymon.ExistingEntriesException;
import com.brieuc.dailymon.entity.entry.EntryFood;
import com.brieuc.dailymon.entity.model.ModelFood;
import com.brieuc.dailymon.repository.EntryFoodRepository;
import com.brieuc.dailymon.repository.ModelFoodRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ModelFoodService {

    private final ModelFoodRepository modelFoodRepository;
    private final EntryFoodRepository entryFoodRepository;


    public ModelFood createModel(String title, String description, int kcal, String image) {
        ModelFood modelFood = new ModelFood(kcal, image);
        modelFood.setTitle(title);
        modelFood.setDescription(description);
        modelFood.setImage(image);
        modelFoodRepository.save(modelFood);
        return modelFood;
    }

    public ModelFood updateModel(ModelFood modelFood, String title, String description, int kcal, String image) {
        modelFood.setTitle(title);
        modelFood.setDescription(description);
        modelFood.setKcal(kcal);
        modelFood.setImage(image);
        modelFoodRepository.save(modelFood);
        return modelFood;
    }

    public List<ModelFood> getModels() {
        return this.modelFoodRepository.findAll();
    }

    public Optional<ModelFood> getModelById(@NonNull UUID modelId) {
        return this.modelFoodRepository.findById(modelId);
    }

    public void deleteModel(ModelFood modelFood) throws ExistingEntriesException {
        List<EntryFood> entries = this.entryFoodRepository.findByModel(modelFood);
        if (entries.stream().count() == 0) {
            this.modelFoodRepository.delete(modelFood);
        }   
        else {
            StringBuilder sb = new StringBuilder("still attached entries for " + modelFood.getTitle());
            sb.append(System.lineSeparator()).append("");
            String sEntries = entries.stream().map((entryFood) -> entryFood.getDate() + " " + entryFood.getTitle()).collect(Collectors.joining(System.lineSeparator()));
            throw new ExistingEntriesException(sb.append(sEntries).toString());
        }
    }
}

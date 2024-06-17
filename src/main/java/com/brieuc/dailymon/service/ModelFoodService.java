package com.brieuc.dailymon.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.brieuc.dailymon.entity.model.ModelFood;
import com.brieuc.dailymon.repository.ModelFoodRepository;

@Service
public class ModelFoodService {

    private ModelFoodRepository modelFoodRepository;

    @Autowired
    public ModelFoodService(ModelFoodRepository modelFoodRepository) {
        this.modelFoodRepository = modelFoodRepository;
    }

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

    public void deleteModel(ModelFood modelFood) {
        this.modelFoodRepository.delete(modelFood);
    }
}

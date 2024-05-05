package com.brieuc.dailymon.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.brieuc.dailymon.dto.ModelDto;
import com.brieuc.dailymon.dto.ModelFoodDto;
import com.brieuc.dailymon.entity.model.Model;
import com.brieuc.dailymon.entity.model.ModelFood;

@Component
public class ModelFacade {

    private ModelFoodService modelFoodService;
    private ModelSportService modelSportService;
    private ModelFreeService modelFreeService;

    @Autowired
    public ModelFacade(ModelSportService modelSportService, ModelFoodService modelFoodService,
                        ModelFreeService modelFreeService) {

        this.modelFoodService = modelFoodService;
        this.modelFreeService = modelFreeService;
        this.modelSportService = modelSportService;   
    }

    public Model createModel(ModelDto modelDto) {
        
        if (modelDto instanceof ModelFoodDto mfood) {
            return this.modelFoodService.createModel(mfood.getTitle(), mfood.getDescription(), mfood.getKcal(), mfood.getImage());
        }
        return null;
    }

    public List<? extends Model> getModels() {
        return this.modelFoodService.getModels();
    }

    public Optional<ModelFood> getModelById(@NonNull UUID modelId) {
        return this.modelFoodService.getModelById(modelId);
    }
}

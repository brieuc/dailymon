package com.brieuc.dailymon.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.brieuc.dailymon.dto.ModelDto;
import com.brieuc.dailymon.dto.ModelFoodDto;
import com.brieuc.dailymon.dto.ModelFreeDto;
import com.brieuc.dailymon.dto.ModelSportDto;
import com.brieuc.dailymon.entity.model.Model;
import com.brieuc.dailymon.entity.model.ModelFood;
import com.brieuc.dailymon.entity.model.ModelFree;
import com.brieuc.dailymon.entity.model.ModelSport;

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
        if (modelDto instanceof ModelSportDto msport) {
            return this.modelSportService.createModel(msport.getTitle(), msport.getDescription(), msport.getSport(), msport.getImage());
        }
        if (modelDto instanceof ModelFreeDto mfree) {
            return this.modelFreeService.createModel(mfree.getTitle(), mfree.getDescription());
        }
        return null;
    }

    public Model updateModel(ModelDto modelDto) {
        if (modelDto instanceof ModelFoodDto mfood) {
            Optional<ModelFood> modelFood = this.modelFoodService.getModelById(mfood.getId());
            if (modelFood.isPresent()) {
                return this.modelFoodService.updateModel(modelFood.get(), mfood.getTitle(), mfood.getDescription(), mfood.getKcal(), mfood.getImage());
            }
        }
        if (modelDto instanceof ModelSportDto msport) {
            Optional<ModelSport> modelSport = this.modelSportService.getModelById(msport.getId());
            if (modelSport.isPresent()) {
                return this.modelSportService.updateModel(modelSport.get(), msport.getTitle(), msport.getDescription(), msport.getSport(), msport.getImage());
            }
         }
        if (modelDto instanceof ModelFreeDto mfree) {
            Optional<ModelFree> modelFree = this.modelFreeService.getModelById(mfree.getId());
            if (modelFree.isPresent()) {
                return this.modelFreeService.updateModel(modelFree.get(), mfree.getTitle(), mfree.getDescription());
            }
        }
        return null;
    }

    public void deleteModel(UUID id) {
        Optional<ModelFood> modelFood = this.modelFoodService.getModelById(id);
        if (modelFood.isPresent()) {
            modelFoodService.deleteModel(modelFood.get());
        }

        Optional<ModelSport> modelSport = this.modelSportService.getModelById(id);
        if (modelSport.isPresent()) {
            modelSportService.deleteModel(modelSport.get());
        }

        Optional<ModelFree> modelFree = this.modelFreeService.getModelById(id);
        if (modelFree.isPresent()) {
            modelFreeService.deleteModel(modelFree.get());
        }
    }

    public List<? extends Model> getModels() {
        return this.modelFoodService.getModels();
    }

    public Optional<ModelFood> getModelById(@NonNull UUID modelId) {
        return this.modelFoodService.getModelById(modelId);
    }
}

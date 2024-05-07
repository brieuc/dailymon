package com.brieuc.dailymon.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brieuc.dailymon.dto.ModelDto;
import com.brieuc.dailymon.dto.ModelFoodDto;
import com.brieuc.dailymon.dto.ModelFreeDto;
import com.brieuc.dailymon.dto.ModelSportDto;
import com.brieuc.dailymon.entity.model.ModelFood;
import com.brieuc.dailymon.entity.model.ModelFree;
import com.brieuc.dailymon.entity.model.ModelSport;
import com.brieuc.dailymon.service.ModelFoodService;
import com.brieuc.dailymon.service.ModelFreeService;
import com.brieuc.dailymon.service.ModelSportService;

@RequestMapping(value =  "/model")
@RestController
public class ModelController {

    private ModelFoodService modelFoodService;
    private ModelSportService modelSportService;
    private ModelFreeService modelFreeService;

    @Autowired
    public ModelController(ModelFoodService modelFoodService,
                           ModelFreeService modelFreeService,
                           ModelSportService modelSportService) {
        this.modelFoodService = modelFoodService;
        this.modelSportService = modelSportService;
        this.modelFreeService = modelFreeService;
    }

    @GetMapping(value = "/food")
    public List<ModelFoodDto> getFoodModels() {
        return modelFoodService.getModels().stream().map(this::toDto).toList();
    }

    @GetMapping(value = "/sport")
    public List<ModelSportDto> getSportModels() {
        return modelSportService.getModels().stream().map(this::toDto).toList();
    }

    @GetMapping(value = "/free")
    public List<ModelFreeDto> getFreeModels() {
        return modelFreeService.getModels().stream().map(this::toDto).toList();
    }

    @GetMapping(value = "/{id}")
    public ModelDto getModel(@PathVariable(name = "id") UUID modelId) {
        Optional<ModelFood> modelFood = modelFoodService.getModelById(modelId);
        if (modelFood.isPresent()) {
            return toDto(modelFood.get());
        }
        Optional<ModelSport> modelSport = modelSportService.getModelById(modelId);
        if (modelSport.isPresent()) {
            return toDto(modelSport.get());
        }
        Optional<ModelFree> modelFree =  modelFreeService.getModelById(modelId);
        if (modelFree.isPresent()) {
            return toDto(modelFree.get());
        }
        return null;
    }

    private ModelFoodDto toDto(ModelFood modelFood) {
        ModelFoodDto modelDto = new ModelFoodDto();
        modelDto.setId(modelFood.getId());
        modelDto.setTitle(modelFood.getTitle());
        modelDto.setDescription(modelFood.getDescription());
        modelDto.setImage(modelFood.getImage());
        modelDto.setKcal(modelFood.getKcal());
        return modelDto;
    }

    private ModelFreeDto toDto(ModelFree modelFree) {
        ModelFreeDto modelDto = new ModelFreeDto();
        modelDto.setId(modelFree.getId());
        modelDto.setTitle(modelFree.getTitle());
        modelDto.setDescription(modelFree.getDescription());
        return modelDto;
    }

    private ModelSportDto toDto(ModelSport modelSport) {
        ModelSportDto modelDto = new ModelSportDto();
        modelDto.setId(modelSport.getId());
        modelDto.setSport(modelSport.getSport());
        modelDto.setTitle(modelSport.getTitle());
        modelDto.setDescription(modelSport.getDescription());
        modelDto.setImage(modelSport.getImage());
        return modelDto;
    }
}

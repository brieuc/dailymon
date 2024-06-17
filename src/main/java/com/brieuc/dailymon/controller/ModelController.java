package com.brieuc.dailymon.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brieuc.dailymon.dto.ModelDto;
import com.brieuc.dailymon.dto.ModelFoodDto;
import com.brieuc.dailymon.dto.ModelFreeDto;
import com.brieuc.dailymon.dto.ModelSportDto;
import com.brieuc.dailymon.entity.model.Model;
import com.brieuc.dailymon.entity.model.ModelFood;
import com.brieuc.dailymon.entity.model.ModelFree;
import com.brieuc.dailymon.entity.model.ModelSport;
import com.brieuc.dailymon.service.ModelFacade;
import com.brieuc.dailymon.service.ModelFoodService;
import com.brieuc.dailymon.service.ModelFreeService;
import com.brieuc.dailymon.service.ModelSportService;

@RequestMapping(value =  "/model")
@RestController
public class ModelController {


    private ModelFoodService modelFoodService;
    private ModelSportService modelSportService;
    private ModelFreeService modelFreeService;

    private ModelFacade modelFacade;

    @Autowired
    public ModelController(ModelFoodService modelFoodService,
                           ModelFreeService modelFreeService,
                           ModelSportService modelSportService,
                           ModelFacade modelFacade) {
        this.modelFoodService = modelFoodService;
        this.modelSportService = modelSportService;
        this.modelFreeService = modelFreeService;
        this.modelFacade = modelFacade;
    }

    @GetMapping(value = "/food")
    public List<ModelDto> getFoodModels() {
        //return modelFoodService.getModels().stream().map(this::toDto).toList();
        return modelFoodService.getModels().stream().sorted((ModelFood f1,  ModelFood f2) -> f1.getTitle().compareTo(f2.getTitle())).map(this::toDto).toList();
    }

    @GetMapping(value = "/sport")
    public List<ModelDto> getSportModels() {
        return modelSportService.getModels().stream().map(this::toDto).toList();
    }

    @GetMapping(value = "/free")
    public List<ModelDto> getFreeModels() {
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

    @PostMapping(value = "/food")
    public ModelDto createModel(@RequestBody ModelFoodDto modelFoodDto) {
        ModelFood modelFood = modelFoodService.createModel(modelFoodDto.getTitle(), modelFoodDto.getDescription(), modelFoodDto.getKcal(), modelFoodDto.getImage());
        return toDto(modelFood);
    } 

    @PutMapping(value = "/{id}/food")
    public ModelDto updateModel(@PathVariable UUID id, @RequestBody ModelFoodDto modelFoodDto) {
        Model model = modelFacade.updateModel(modelFoodDto);
        return toDto(model);
    } 

    @PostMapping(value = "/sport")
    public ModelDto createModel(@RequestBody ModelSportDto modelSportDto) {
        ModelSport modelSport = modelSportService.createModel(modelSportDto.getTitle(), modelSportDto.getDescription(), modelSportDto.getSport(), modelSportDto.getImage());
        return toDto(modelSport);
    } 

    @PostMapping(value = "/free")
    public ModelDto createModel(@RequestBody ModelFreeDto modelFreeDto) {
        ModelFree modelFree = modelFreeService.createModel(modelFreeDto.getTitle(), modelFreeDto.getTitle());
        return toDto(modelFree);
    } 

    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable(name = "id") UUID id) {
        modelFacade.deleteModel(id);
    }

    private ModelDto toDto(Model model) {
        if (model instanceof ModelFood modelFood) {
            ModelFoodDto modelDto = new ModelFoodDto();
            modelDto.setId(modelFood.getId());
            modelDto.setTitle(modelFood.getTitle());
            modelDto.setDescription(modelFood.getDescription());
            modelDto.setImage(modelFood.getImage());
            modelDto.setKcal(modelFood.getKcal());
            return modelDto;
        }
        if (model instanceof ModelSport modelSport) {
            ModelSportDto modelDto = new ModelSportDto();
            modelDto.setId(modelSport.getId());
            modelDto.setSport(modelSport.getSport());
            modelDto.setTitle(modelSport.getTitle());
            modelDto.setDescription(modelSport.getDescription());
            modelDto.setImage(modelSport.getImage());
            return modelDto;
        }
        if (model instanceof ModelFree modelFree) {
            ModelFreeDto modelDto = new ModelFreeDto();
            modelDto.setId(modelFree.getId());
            modelDto.setTitle(modelFree.getTitle());
            modelDto.setDescription(modelFree.getDescription());
            return modelDto;
        }
        return null;
    }
}

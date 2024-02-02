package com.brieuc.dailymon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brieuc.dailymon.dto.ModelDto;
import com.brieuc.dailymon.entity.Model;
import com.brieuc.dailymon.service.ModelService;

@RequestMapping(value =  "/model", produces = "application/json")
@RestController
public class ModelController {

    private ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping
    @ResponseBody
    //public HashMap<UUID, ModelDto> getModels() {
    public List<ModelDto> getModels() {
        return this.modelService.getModels().stream().map(this::toDto).toList();
        /*
        HashMap<UUID, ModelDto> map = new HashMap<>();
        List<ModelDto> modelDtos = this.modelService.getModels().stream().map(this::toDto).toList();
        for (ModelDto modelDto : modelDtos) {
            map.put(modelDto.getId(), modelDto);
        }
        return map;
        */
    }

    private ModelDto toDto(Model model) {
        ModelDto modelDto = new ModelDto();
        modelDto.setId(model.getId());
        modelDto.setLabel(model.getLabel());
        modelDto.setDescription(model.getDescription());
        modelDto.setImage(model.getImage());
        modelDto.setKcal(model.getKcal());
        return modelDto;
    }
}

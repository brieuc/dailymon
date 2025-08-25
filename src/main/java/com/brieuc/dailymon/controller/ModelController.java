package com.brieuc.dailymon.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brieuc.dailymon.ExistingEntriesException;
import com.brieuc.dailymon.dto.ModelDto;
import com.brieuc.dailymon.dto.ModelFoodDto;
import com.brieuc.dailymon.entity.model.Model;
import com.brieuc.dailymon.entity.model.ModelFood;
import com.brieuc.dailymon.mapper.ModelMapper;
import com.brieuc.dailymon.service.ModelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value =  "/model")
@RestController
public class ModelController {

    private final ModelService modelService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/{id}")
    public ModelDto getModel(@PathVariable(name = "id") UUID modelId) {
        return modelMapper.toDto(modelService.getModelById(modelId));
    }

    @PostMapping
    public ModelDto createModel(@RequestBody ModelDto modelDto) {
        Model model = modelService.createModel(modelMapper.toEntity(modelDto)));
        return modelMapper.toDto(model);
    } 

    @PutMapping(value = "/{id}")
    public ModelDto updateModel(@PathVariable UUID id, @RequestBody ModelDto modelDto) {
        Model model = modelService.updateModel(modelMapper.toEntity(modelDto));
        return modelMapper.toDto(model);
    } 

    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable(name = "id") UUID id) throws ExistingEntriesException {
        modelService.deleteModel(id);
    }
}

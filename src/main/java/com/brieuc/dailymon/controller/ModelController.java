package com.brieuc.dailymon.controller;

import java.util.List;
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
import com.brieuc.dailymon.entity.model.Model;
import com.brieuc.dailymon.mapper.ModelMapper;
import com.brieuc.dailymon.service.ModelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value =  "/model")
@RestController
public class ModelController {

    private final ModelService modelService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/food")
    public List<ModelDto> getFoodModels() {
        return modelService.getFoodModels().stream().map(f -> modelMapper.toDto(f)).toList();
    }

    @GetMapping(value = "/free")
    public List<ModelDto> getFreeModels() {
        return modelService.getFreeModels().stream().map(f -> modelMapper.toDto(f)).toList();
    }

    @GetMapping(value = "/sport")
    public List<ModelDto> getSportModels() {
        return modelService.getSportModels().stream().map(f -> modelMapper.toDto(f)).toList();
    }

    @GetMapping(value = "/{id}")
    public ModelDto getModel(@PathVariable(name = "id") UUID modelId) {
        Model model = modelService.getModelById(modelId).orElseThrow(() -> new RuntimeException("Model Id does not exist"));
        return modelMapper.toDto(model);
    }

    @PostMapping
    public ModelDto createModel(@RequestBody ModelDto modelDto) {
        Model model = modelService.createModel(modelMapper.toEntity(modelDto));
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

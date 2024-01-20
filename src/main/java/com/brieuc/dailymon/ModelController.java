package com.brieuc.dailymon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brieuc.dailymon.entity.Model;

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
    //@CrossOrigin(origins = "http://localhost:8081")
    public List<ModelDto> getModels() {
        return this.modelService.getModels().stream().map(this::toDto).toList();
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

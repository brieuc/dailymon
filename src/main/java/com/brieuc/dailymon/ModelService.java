package com.brieuc.dailymon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.brieuc.dailymon.entity.Model;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Service
public class ModelService {

    private ModelRepository modelRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }
    
    public List<Model> getModels() {
        return this.modelRepository.findAll();
    }
}

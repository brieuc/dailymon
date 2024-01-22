package com.brieuc.dailymon.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.brieuc.dailymon.entity.Model;
import com.brieuc.dailymon.repository.ModelRepository;

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

    public Optional<Model> getModelById(UUID modelId) {
        return this.modelRepository.findById(modelId);
    }
}

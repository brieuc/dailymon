package com.brieuc.dailymon.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.brieuc.dailymon.entity.model.ModelFree;
import com.brieuc.dailymon.repository.ModelFreeRepository;

@Service
public class ModelFreeService {

    private ModelFreeRepository modelFreeRepository;

    @Autowired
    public ModelFreeService(ModelFreeRepository modelRepository) {
        this.modelFreeRepository = modelRepository;
    }
    
    public ModelFree createModel(String title, String description) {
        ModelFree modelFree = ModelFree.builder()
            .id(UUID.randomUUID())
            .title(title)
            .description(description)
            .build();
        modelFreeRepository.save(modelFree);
        return modelFree;
    }

    public ModelFree updateModel(ModelFree modelFree, String title, String description) {
        modelFree.setTitle(title);
        modelFree.setDescription(description);
        modelFreeRepository.save(modelFree);
        return modelFree;
    }

    public List<ModelFree> getModels() {
        return this.modelFreeRepository.findAll();
    }

    public Optional<ModelFree> getModelById(@NonNull UUID modelId) {
        return this.modelFreeRepository.findById(modelId);
    }

        public void deleteModel(ModelFree modelFree) {
        this.modelFreeRepository.delete(modelFree);
    }
}

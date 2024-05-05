package com.brieuc.dailymon.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.brieuc.dailymon.entity.Benefit;
import com.brieuc.dailymon.entity.Sport;
import com.brieuc.dailymon.entity.model.ModelSport;
import com.brieuc.dailymon.repository.ModelSportRepository;

@Service
public class ModelSportService {

    private final ModelSportRepository modelSportRepository;

    @Autowired
    public ModelSportService(ModelSportRepository modelSportRepository) {
        this.modelSportRepository = modelSportRepository;
    }

    public ModelSport createModel(Sport sport, Benefit benefit, String image) {
        ModelSport modelSport = new ModelSport(sport, image);
        modelSportRepository.save(modelSport);
        return modelSport;
    }

    public List<ModelSport> getModels() {
        return this.modelSportRepository.findAll();
    }

    public Optional<ModelSport> getModelById(@NonNull UUID modelId) {
        return this.modelSportRepository.findById(modelId);
    }
}

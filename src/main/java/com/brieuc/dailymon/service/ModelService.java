package com.brieuc.dailymon.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.lang.NonNull;

import com.brieuc.dailymon.dto.ModelDto;
import com.brieuc.dailymon.entity.model.Model;

public interface ModelService {
      List<Model> getModels();
      Optional<Model> getModelById(@NonNull UUID modelId);
      Model createModel(Model model);
      Model updateModel(Model model);
      void deleteModel(UUID id);
}

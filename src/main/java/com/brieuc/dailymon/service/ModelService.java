package com.brieuc.dailymon.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.lang.NonNull;

import com.brieuc.dailymon.entity.model.Model;
import com.brieuc.dailymon.entity.model.ModelFood;

public interface ModelService {
      List<Model> getModels();

      List<ModelFood> getFoodModels();
      List<? extends Model> getFreeModels();
      List<? extends Model> getSportModels();

      Optional<Model> getModelById(@NonNull UUID modelId);
      Model createModel(Model model);
      Model updateModel(Model model);
      void deleteModel(UUID id);
}

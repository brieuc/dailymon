package com.brieuc.dailymon.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.brieuc.dailymon.dto.ModelDto;
import com.brieuc.dailymon.dto.ModelFoodDto;
import com.brieuc.dailymon.dto.ModelFreeDto;
import com.brieuc.dailymon.dto.ModelSportDto;
import com.brieuc.dailymon.entity.model.Model;
import com.brieuc.dailymon.entity.model.ModelFood;
import com.brieuc.dailymon.entity.model.ModelFree;
import com.brieuc.dailymon.entity.model.ModelSport;
import com.brieuc.dailymon.mapper.ModelMapper;
import com.brieuc.dailymon.repository.ModelRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {

      private final ModelRepository modelRepository;
      private final ModelMapper modelMapper;

      public List<Model> getModels() {
            return modelRepository.findAll();
      }

      public Optional<Model> getModelById(@NonNull UUID modelId) {
            return modelRepository.findById(modelId);
      }
      
      public Model createModel(ModelDto modelDto) {

            Model model = switch (modelDto) {
                  case ModelFoodDto modelFoodDto -> {
                        Model m = ModelFood.builder()
                        // .propriétés depuis modelFoodDto
                        .build();
                        modelRepository.save(m);
                        yield m;
                  }
                  case ModelFreeDto modelFreeDto -> {
                        Model m = ModelFree.builder()
                        // .propriétés depuis modelFreeDto
                        .build();
                        modelRepository.save(m);
                        yield m;
                  }
                  case ModelSportDto modelSportDto -> {
                        Model m = ModelSport.builder()
                        // .propriétés depuis modelSportDto
                        .build();
                        modelRepository.save(m);
                        yield m;
                  }
                  default -> throw new IllegalArgumentException("Type de DTO non supporté: " + modelDto.getClass());
            };
    
            return model;
      }

      public Model updateModel(ModelDto modelDto) {
            // Récupérer l'entité existante
            Model existingModel = modelRepository.findById(modelDto.getId())
                  .orElseThrow(() -> new EntityNotFoundException("Model not found with id: " + modelDto.getId()));
            
            // Mettre à jour selon le type
            Model updatedModel = switch (modelDto) {
                  case ModelFoodDto foodDto -> {
                        ModelFood foodModel = (ModelFood) existingModel;
                        // Mettre à jour les propriétés
                        foodModel.setTitle(foodDto.getTitle());
                        foodModel.setDescription(foodDto.getDescription());
                        foodModel.setKcal(foodDto.getKcal());
                        // ... autres propriétés
                        yield foodModel;
                  }
                  case ModelFreeDto freeDto -> {
                        ModelFree freeModel = (ModelFree) existingModel;
                        freeModel.setTitle(freeDto.getTitle());
                        freeModel.setDescription(freeDto.getDescription());
                        // ... autres propriétés
                        yield freeModel;
                  }
                  case ModelSportDto sportDto -> {
                        ModelSport sportModel = (ModelSport) existingModel;
                        sportModel.setTitle(sportDto.getTitle());
                        sportModel.setDescription(sportDto.getDescription());
                        // ... autres propriétés
                        yield sportModel;
                  }
                  default -> throw new IllegalArgumentException("Type de DTO non supporté: " + modelDto.getClass());
            };
            
            return modelRepository.save(updatedModel);
      }

      public void deleteModel(UUID id) {
            Model model = modelRepository.findById(id)
                  .orElseThrow(() -> new EntityNotFoundException("Model not found with id: " + id));
            
            try {
                  modelRepository.delete(model);
            } catch (DataIntegrityViolationException e) {
                  throw new IllegalStateException("Cannot delete model with id " + id + " - it may be referenced by other entities", e);
            }
      }
}
